package educally

import grails.converters.JSON
import grails.converters.XML

class EvaluationController {

    static scaffold = true

    static allowedMethods = [sortSkills: 'POST']

    def evaluationService
    def skillService
    def teacherService

    def manage() {

        Teacher teacher = teacherService.currentTeacher
        SkillBook skillBook = SkillBook.get(params.int('skillBookId'))

        def evaluations = teacher.pupils.flatten().evaluations.flatten().groupBy { x -> x.values.sort { y -> y.skillId }.skillId }
        def sortedSkills = skillBook.skills.sort { it.path }

        BigDecimal skillCount = sortedSkills.findAll { !it.name }.size()
        BigDecimal evaluatedCount = evaluations.values().flatten().size()
        def skillCoverage = skillCount > 0 ? Math.round(evaluatedCount / skillCount * 100) : 0

        List<SkillModel> skillModels = []
        sortedSkills.each { x -> skillModels.add(new SkillModel(skill: x)) }

        for (entry in evaluations) {
            for (skillId in entry.key) {
                def skillModel = skillModels.find { it.skill.id == skillId }

                for (evaluation in entry.value) {
                    def linkedSkills = skillModel.evaluations.put(evaluation, [])
                    for (evaluatedSkill in evaluation.values) {
                        def skill = sortedSkills.find { it.id == evaluatedSkill.skill.id }
                        linkedSkills.add(skill)
                    }
                }

                skillModel.stats.put('count', evaluations.size())
            }
        }

        respond skillModel, model: [sortedSkills: sortedSkills as JSON, skillBook: skillBook, skillCoverage: skillCoverage]
    }

}
