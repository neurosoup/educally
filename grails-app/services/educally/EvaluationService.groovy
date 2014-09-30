package educally

import grails.transaction.Transactional
import org.joda.time.LocalDateTime

@Transactional
class EvaluationService {

    def Evaluation createEvaluationForPupil(List<String> tags, Pupil pupil, String title, NotationSystem preferredNotationSystem, Skill skill, BigDecimal value = null) {

        def evaluation = new Evaluation(tags: tags, title: title).addToPreferredNotationSystems(preferredNotationSystem)
        evaluation.addToValues(new EvaluatedSkill(skill: skill, value: value, missed: !value, dateTime: LocalDateTime.now()))
        pupil.addToEvaluations(evaluation)
        pupil.save()

        evaluation
    }

    def addEvaluationValue(Evaluation evaluation, Skill skill, BigDecimal value) {
        evaluation.addToValues(new EvaluatedSkill(skill: skill, value: value, missed: !value, dateTime: LocalDateTime.now()))
        evaluation.save()
    }

    def Integer calculateSkillCoverage(Teacher teacher, SkillBook skillBook) {

        BigDecimal skillCount = Skill.countBySkillBook(skillBook)
        BigDecimal evaluatedCount = teacher.pupils.evaluations.values.skill.flatten().collect { it.id }.unique().size().toBigDecimal()

        if (skillCount > 0) {
            Math.round(evaluatedCount / skillCount * 100)
        } else {
            0
        }

    }

}

