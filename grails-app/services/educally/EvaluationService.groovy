package educally

import grails.plugin.cache.Cacheable
import grails.transaction.Transactional
import org.joda.time.LocalDateTime

@Transactional
class EvaluationService {

    // All domain instances must have been saved before !
    def teacherRatePupil(Teacher teacher, Pupil pupil, Evaluation evaluation, Skill skill, BigDecimal value = null) {

        def rating = new Rating(skill: skill, evaluation: evaluation, value: value, missed: !value, dateTime: LocalDateTime.now())
        pupil.addToRatings(rating)

        //stats

        skill.stats = skill.stats ?: new SkillStats()
        skill.stats.evaluationCount = teacher.pupils.findAll {
            it.ratings.any {
                it.skill == skill
            }
        }.ratings.evaluation.unique().size()

        def ratings = teacher.pupils.ratings.value.flatten() - null
        skill.stats.averageRating = ratings.sum() / ratings.size()

        def skillBook = teacher.skillBooks.find { it.skills.any { it.id == skill.id } }
        skillBook.stats = skill.skillBook.stats ?: new SkillBookStats()
        skillBook.stats.skillCoverage = skill.stats.evaluationCount / skill.skillBook.skills.findAll {
            it.name == null
        }.size()

        teacher.save()

        evaluation.stats = evaluation.stats ?: new EvaluationStats()
        def nonZeroRating = teacher.pupils.ratings.flatten().findAll { it?.value > 0 }
        def simpleRating = teacher.pupils.ratings.flatten().findAll { it?.value >= 0 }

        evaluation.stats.nonRatedcount = teacher.pupils.minus(teacher.pupils.findAll {
            it.ratings.any { it.evaluation == evaluation }
        }).size()
        evaluation.stats.ratingMissedCount = teacher.pupils.ratings.findAll { it.any { it.missed } }.size()
        evaluation.stats.zeroRatingCount = teacher.pupils.ratings.findAll { it.any { it.value == 0 } }.size()
        evaluation.stats.nonZeroRatingCount = nonZeroRating.size()
        evaluation.stats.simpleRatingCount = simpleRating.size()

        evaluation.stats.nonZeroRatingAverage = nonZeroRating.value.sum() / nonZeroRating.size()
        evaluation.stats.simpleRatingAverage = simpleRating.value.sum() / simpleRating.size()


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

