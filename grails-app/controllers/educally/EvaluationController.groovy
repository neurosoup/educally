package educally

import grails.async.Promise


class EvaluationController {

    static scaffold = true

    def evaluationService
    def skillService
    def teacherService

    def manage() {

        Teacher teacher = teacherService.currentTeacher
        SkillBook skillBook = SkillBook.get(params.int('skillBookId'))

        def evaluationCount = teacher.pupils.flatten().evaluations.flatten().size()
        def skillCoverage = evaluationService.calculateSkillCoverage(teacher, skillBook)
        def skills = skillBook.skills.sort { it.path }

        respond skills, model: [skillBookTitle: skillBook.title, evaluationCount: evaluationCount, skillCoverage: skillCoverage]

    }

    def sortSkills() {
        SkillBook skillBook = SkillBook.get(params.int('skillBookId'))
        skillBook.skills.sort { it.path }
    }
}
