package educally

class EvaluationController {

    def evaluationService

    def index() {
        Skill skill = Skill.get(params.int('skillId'))
        evaluationService.getEvaluationsBySkill(skill)
    }

}
