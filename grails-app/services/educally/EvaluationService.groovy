package educally

import grails.plugin.cache.CachePut
import grails.plugin.cache.Cacheable
import grails.transaction.Transactional
import org.joda.time.LocalDateTime

@Transactional
class EvaluationService {

    @Cacheable(value = 'evaluationsBySkill', key = "#skill.id")
    def getEvaluationsBySkill(Skill skill) {
        Rating.findAllBySkill(skill).evaluation.unique { it.id }
    }

    def Evaluation create(String[] tags, String title, NotationSystem preferredNotationSystem) {
        new Evaluation(tags: tags, title: title)
                .addToPreferredNotationSystems(preferredNotationSystem)
                .save(failOnError: true)
    }

    @CachePut(value = 'evaluationBySkill', key = '#skill.id')
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

        log.info("teacher ${teacher.account.firstName} ${teacher.account.firstName} gave pupil ${pupil.firstName} ${pupil.lastName} a rate of ${rating.value} for evaluation ${evaluation.title} (${evaluation.tags}) ")

        updateSkillStats(teacher, skill)
        updateSkillBookStats(teacher, skill.skillBook)
        updateEvaluationStats(teacher, evaluation)
        updateParentSkillStats(teacher, skill.root)
    }

    def updateSkillStats(Teacher teacher, Skill skill) {

        def ratings = skill.ratings
        skill.stats = skill.stats ?: new SkillStats()
        skill.stats.evaluationCount = ratings.evaluation.unique().size()

        def values = ratings.value - null
        def sum = values.sum() ?: 0.0

        skill.stats.ratingSum = sum as BigDecimal
        skill.stats.ratingCount = ratings.size()
        skill.stats.averageRating = sum / ratings.size()

        skill.save()

        log.debug("stats updated for skill ${teacher.account.firstName} ${teacher.account.lastName} -> ${skill.skillBook.title} -> ${skill.title} :\n${skill.stats}")

    }

    def updateParentSkillStats(Teacher teacher, Skill skill) {

        def evaluationCount = 0
        def ratingSum = 0
        def ratingCount = 0

        if (skill.name != null) {
            skill.children.each {

                def stats = updateParentSkillStats(teacher, it)

                evaluationCount += stats.evaluationCount
                ratingSum += stats.ratingSum
                ratingCount += stats.ratingCount
            }

            skill.stats = skill.stats ?: new SkillStats()
            skill.stats.evaluationCount = evaluationCount
            skill.stats.ratingCount = ratingCount
            skill.stats.ratingSum = ratingSum
            skill.stats.averageRating = ratingCount > 0 ? ratingSum / ratingCount : 0

            skill.save()
            log.debug("stats updated for skill ${teacher.account.firstName} ${teacher.account.lastName} -> ${skill.skillBook.title} -> ${skill.title} :\n${skill.stats}")

            evaluationCount = 0;
            ratingSum = 0;
            ratingCount = 0

        } else {
            if (skill.stats) {
                evaluationCount = skill.stats.evaluationCount
                ratingSum = skill.stats.ratingSum
                ratingCount = skill.stats.ratingCount
            } else {
                evaluationCount = 0
                ratingSum = 0
                ratingCount = 0
            }
        }

        return [evaluationCount: evaluationCount, ratingCount: ratingCount, ratingSum: ratingSum]
    }

    def updateSkillBookStats(Teacher teacher, SkillBook skillBook) {

        def leaves = Skill.findAllBySkillBookAndNameIsNull(skillBook)
        def rated = Skill.findAllBySkillBookAndNameIsNull(skillBook).findAll {
            it.ratings != null && it.ratings.size() > 0
        }

        skillBook.stats = skillBook.stats ?: new SkillBookStats()
        skillBook.stats.skillCoverage = rated.size() / leaves.size()
        skillBook.save()

        log.debug("stats updated for skillBook ${teacher.account.firstName} ${teacher.account.lastName} -> ${skillBook.title} :\n${skillBook.stats}")
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

        log.debug("stats updated for evaluation ${teacher.account.firstName} ${teacher.account.lastName} -> ${evaluation.title} :\n${evaluation.stats}")
    }

}

