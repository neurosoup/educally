package educally

import grails.plugin.cache.CachePut
import grails.plugin.cache.Cacheable
import grails.transaction.Transactional
import org.joda.time.LocalDateTime

@Transactional
class EvaluationService {

    @Cacheable(value = 'evaluationsBySkill', key = "#skill")
    def getEvaluationsBySkill(Skill skill) {
        Rating.findAllBySkill(skill).evaluation.unique()
    }

    def Evaluation create(String[] tags, String title, NotationSystem preferredNotationSystem) {
        new Evaluation(tags: tags, title: title)
                .addToPreferredNotationSystems(preferredNotationSystem)
                .save(failOnError: true)
    }

    @CachePut(value = 'evaluationBySkill', key = '#skill')
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

        skill.stats.averageRating = sum / ratings.size()
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

}

