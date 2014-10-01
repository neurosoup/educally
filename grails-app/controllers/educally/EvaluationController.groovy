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

        def evaluations = teacher.pupils.flatten().evaluations
        def skillCoverage = evaluationService.calculateSkillCoverage(teacher, skillBook)
        def skills = skillBook.skills.sort { it.path }

        def groupedEvaluations = evaluations.values.flatten().groupBy { it.skillId }

        def evaluatedSkills = []

        groupedEvaluations.each { x ->
            evaluatedSkills.add([skill: skills.find { y -> y.id == x.key }, evaluations: x.value])
        }

        [skills: skills as JSON, evaluatedSkills: evaluations, skillBookId: skillBook.id, skillBookTitle: skillBook.title, evaluationCount: evaluations.size(), skillCoverage: skillCoverage]
    }

}
