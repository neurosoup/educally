package educally

class EvaluationController {

    def evaluationService
    def skillService

    def index() {
        SkillBook skillBook = SkillBook.get(params.int('skillBookId'))
        def skills = skillService.getSkillsBySkillBook(skillBook)
        [skillBook: skillBook, skills: skills]
    }

    def list() {
        Skill skill = Skill.get(params.int('skillId'))
        def evaluations = evaluationService.getEvaluationsBySkill(skill)
        render template: 'evaluations', model: [skill: skill, evaluations: evaluations]
    }

}
