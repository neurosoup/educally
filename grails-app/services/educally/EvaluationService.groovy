package educally

import grails.transaction.Transactional
import org.codehaus.groovy.runtime.DefaultGroovyMethodsSupport

@Transactional
class EvaluationService {

    def Evaluation createEvaluationForPupil(List<String> tags, Pupil pupil, String title, NotationSystem preferredNotationSystem, Skill skill, BigDecimal value = null) {

        def evaluation = new Evaluation(tags: tags, title: title).addToPreferredNotationSystems(preferredNotationSystem)
        evaluation.values.add(new EvaluatedSkill(skill: skill, value: value, missed: !value))
        skill.addToEvaluations(evaluation)
        pupil.addToEvaluations(evaluation)
        pupil.save()

        evaluation
    }

    def addEvaluationValue(Evaluation evaluation, Skill skill, BigDecimal value) {
        def toto = []
        toto.each {}
        evaluation.values.add(new EvaluatedSkill(skill: skill, value: value, missed: !value))
        evaluation.save()
    }
}

