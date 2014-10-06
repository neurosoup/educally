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

        List<SkillModel> skillModels
        sortedSkills.each { x ->
            skillModels.add(new SkillModel(skill: x))
        }
        evaluations.each { k, v ->
            k.each { skillId ->
                def skillModel = skillModels.find { it.skill.id == skillId }
                v.each {evaluation ->
                    def linkedSkills = skillModel.evaluations.put(evaluation, [])
                    evaluation.values.each {evaluatedSkill ->
                        def skill = sortedSkills.find { it.id == evaluatedSkill.skill.id }
                        linkedSkills.add(skill)
                    }
                }
                skillModel.stats << [count: v.size()]
            }
        }

        respond skillsAndEvaluations, model: [sortedSkills: sortedSkills as JSON, skillBook: skillBook, skillCoverage: skillCoverage]
    }

}
