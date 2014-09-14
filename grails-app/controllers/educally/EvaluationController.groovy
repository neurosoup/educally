package educally

import grails.async.Promise


class EvaluationController {

    static scaffold = true

    def evaluationService
    def asyncEvaluationService
    def teacherService

    def manage() {
        Teacher teacher = teacherService.currentTeacher
        SkillBook skillBook = SkillBook.get(params.int('skillBookId'))

        def skillCoverage = evaluationService.calculateSkillCoverage(teacher, skillBook)
        respond skillBook.skills, model: [skillBookTitle: skillBook.title, evaluationCount: 15, skillCoverage: skillCoverage]

    }
}
