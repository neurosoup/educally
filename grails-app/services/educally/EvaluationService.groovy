package educally

import grails.plugin.cache.Cacheable
import grails.transaction.Transactional
import org.joda.time.LocalDateTime

@Transactional
class EvaluationService {

    def Evaluation create(String[] tags, String title, NotationSystem preferredNotationSystem) {
        new Evaluation(tags: tags, title: title)
                .addToPreferredNotationSystems(preferredNotationSystem)
                .save(failOnError: true)
    }


    def rate(Teacher teacher, Pupil pupil, Evaluation evaluation, Skill skill, BigDecimal value = null) {

        def rating = new Rating(value: value, missed: value == null, dateTime: LocalDateTime.now())

        teacher.addToEvaluations(evaluation)
        teacher.save()

        skill.addToRatings(rating)
        skill.save()

        evaluation.addToRatings(rating)
        evaluation.save()

        pupil.addToRatings(rating)
        pupil.save()

        updateSkillStats(skill)
        updateSkillBookStats(skill.skillBook)
        updateEvaluationStats(teacher, evaluation)
    }

    def updateSkillStats(Skill skill) {

        def ratings = skill.ratings
        skill.stats = skill.stats ?: new SkillStats()
        skill.stats.evaluationCount = ratings.evaluation.size()


        def values = ratings.value - null
        def sum = values.sum() ?: 0.0

        skill.stats.averageRating =  sum / ratings.size()
        skill.save()
    }

    def updateSkillBookStats(SkillBook skillBook) {

        def leaves = Skill.findAllBySkillBookAndNameIsNull(skillBook)
        def rated = Skill.findAllBySkillBookAndNameIsNull(skillBook).findAll {
            it.ratings != null && it.ratings.size() > 0
        }

        skillBook.stats = skillBook.stats ?: new SkillBookStats()
        skillBook.stats.skillCoverage = rated.size() / leaves.size()
        skillBook.save()
    }

    def updateEvaluationStats(Teacher teacher, Evaluation evaluation) {

        def allRatings = Rating.findAllByEvaluationAndMissed(evaluation, false)
        def zeroRating = Rating.findAllByEvaluationAndMissedAndScaledValue(evaluation, false, 0)
        def nonZeroRating = Rating.findAllByEvaluationAndScaledValueGreaterThan(evaluation, 0)
        def missedRatings = Rating.findAllByEvaluationAndMissed(evaluation, true)

        evaluation.stats = evaluation.stats ?: new EvaluationStats()
        evaluation.stats.nonRatedcount = teacher.classRoom.pupils.size() - allRatings.pupil.unique().size()

        evaluation.stats.ratingMissedCount = missedRatings.size()
        evaluation.stats.zeroRatingCount = zeroRating.size()
        evaluation.stats.nonZeroRatingCount = nonZeroRating.size()
        evaluation.stats.simpleRatingCount = allRatings.size()

        def nonZeroRatingSum = nonZeroRating.value.sum() ?: 0.0
        def allRatingsSum = allRatings.value.sum() ?: 0.0

        evaluation.stats.nonZeroRatingAverage = nonZeroRating.size() > 0 ? nonZeroRatingSum / nonZeroRating.size() : 0.0
        evaluation.stats.simpleRatingAverage = allRatings.size() > 0 ? allRatingsSum / allRatings.size() : 0.0
        evaluation.save()
    }


    @Cacheable(value = 'skillsAndEvaluationsByTeacherAndSkillBook', key = "{ #teacher.id, #skillBook.id }")
    def Expando GetSkillsAndEvaluationsByTeacherAndSkillBook(Teacher teacher, SkillBook skillBook) {

        def evaluationsBySkill = teacher.pupils.ratings.groupBy { x -> x.skill }
        def skills = skillBook.skills.sort { it.path }

        def evaluations = teacher.pupils.flatten().evaluations.flatten().groupBy { x -> x.ratings.sort { y -> y.skillId }.skillId }
        def sortedSkills = skillBook.skills.sort { it.path }

        def model = new Expando()
        model.skills = []
        model.stats = [:]
        model.skillBook = skillBook
        model.stats.put('total', skillCount)
        model.stats.put('count', evaluatedCount)
        model.stats.put('coverage', skillCoverage)

        sortedSkills.each { x ->
            def skill = new Expando()
            skill.stats = [:]
            skill.domainInstance = x
            model.skills.add(skill)
        }

        for (entry in evaluations) {
            for (skillId in entry.key) {
                def skill = model.skills.find { it.domainInstance.id == skillId }
                skill.evaluations = []
                skill.linkedSkills = []
                for (evaluation in entry.value) {
                    skill.evaluations.add(evaluation)
                    for (value in evaluation.ratings) {
                        def s = sortedSkills.find { it.id == value.skill.id }
                        def linkedSkill = new Expando()
                        if (s.id != skillId && !skill.linkedSkills.any { y -> y.id == s.id }) {
                            linkedSkill.id = s.id
                            linkedSkill.title = s.title
                            skill.linkedSkills.add(linkedSkill)
                        }
                    }
                }
                skill.stats.put('evaluationCount', skill.evaluations.size())
            }
        }
        model
    }

}

