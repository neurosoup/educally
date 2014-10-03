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
        def skills = skillBook.skills.sort { it.path }
        BigDecimal skillCount = skills.findAll { !it.name }.size()
        BigDecimal evaluatedCount = evaluations.values().flatten().size()
        def skillCoverage = skillCount > 0 ? Math.round(evaluatedCount / skillCount * 100) : 0

        def evaluatedSkills = []
        evaluations.each { k, v ->
            k.each {x ->
                evaluatedSkills.add([skill: skills.find { y -> y.id == x }, evaluations: v])
            }
        }

        [skills: skills as JSON, evaluatedSkills: evaluatedSkills, skillBookId: skillBook.id, skillBookTitle: skillBook.title, evaluationCount: evaluations.size(), skillCoverage: skillCoverage]
    }

}
