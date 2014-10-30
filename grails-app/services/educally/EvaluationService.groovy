package educally

import grails.plugin.cache.Cacheable
import grails.transaction.Transactional
import org.joda.time.LocalDateTime

@Transactional
class EvaluationService {

    def teacherRateSkillEvaluationForPupil(Teacher teacher, Pupil pupil, Evaluation evaluation, Skill skill, BigDecimal value = null) {

        def rating = new Rating(value: value, missed: !value, awaiting: false, dateTime: LocalDateTime.now())
        def teacherSkill = teacher.skillBooks.skills.flatten().find { it.id == skill.id }
        def teacherPupil = teacher.pupils.find { it.id == pupil.id }
        def teacherEvaluation = teacher.evaluations.find { it.id == evaluation.id }

        teacherSkill.addToRatings(rating)
        teacherPupil.addToRatings(rating)
        teacherEvaluation.addToRatings(rating)

        updateStats(teacher, teacherSkill, teacherEvaluation, teacherSkill.skillBook)

        teacher.save()
    }

    def updateStats(Teacher teacher, Skill teacherSkill, Evaluation teacherEvaluation, SkillBook teacherSkillBook) {

        def skillRatings = Rating.findAllBySkill(teacherSkill)

        teacherSkill.stats = teacherSkill.stats ?: new SkillStats()
        teacherSkill.stats.evaluationCount = skillRatings.evaluation.size()
        teacherSkill.stats.averageRating = skillRatings.sum() / skillRatings.size()

        def teacherSkills = Skill.findBySkillBook(teacherSkillBook).findAll { it.name == null }
        def evaluatedSkills = Skill.findBySkillBook(teacherSkillBook).findAll { it.name == null && it.}
        teacherSkillBook.stats = teacherSkill.skillBook.stats ?: new SkillBookStats()
        teacherSkillBook.stats.skillCoverage = Skill
                teacherSkillBook.stats.skillCoverage = teacherSkill.stats.evaluationCount / teacherSkill.skillBook.skills.findAll {
            it.name == null
        }.size()

        teacher.save()

        teacherEvaluation.stats = teacherEvaluation.stats ?: new EvaluationStats()
        def nonZeroRating = teacher.pupils.ratings.flatten().findAll { it?.value > 0 }
        def simpleRating = teacher.pupils.ratings.flatten().findAll { it?.value >= 0 }

        teacherEvaluation.stats.nonRatedcount = teacher.pupils.minus(teacher.pupils.findAll {
            it.ratings.any { it.evaluation == teacherEvaluation }
        }).size()
        teacherEvaluation.stats.ratingMissedCount = teacher.pupils.ratings.findAll { it.any { it.missed } }.size()
        teacherEvaluation.stats.zeroRatingCount = teacher.pupils.ratings.findAll { it.any { it.value == 0 } }.size()
        teacherEvaluation.stats.nonZeroRatingCount = nonZeroRating.size()
        teacherEvaluation.stats.simpleRatingCount = simpleRating.size()

        teacherEvaluation.stats.nonZeroRatingAverage = nonZeroRating.value.sum() / nonZeroRating.size()
        teacherEvaluation.stats.simpleRatingAverage = simpleRating.value.sum() / simpleRating.size()

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

