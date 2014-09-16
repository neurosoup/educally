package educally

import grails.converters.JSON

class EvaluationController {

    static scaffold = true

    static allowedMethods = [sortSkills: 'POST']

    def evaluationService
    def skillService
    def teacherService

    def manage() {

        Teacher teacher = teacherService.currentTeacher
        SkillBook skillBook = SkillBook.get(params.int('skillBookId'))

        def evaluationCount = teacher.pupils.flatten().evaluations.flatten().size()
        def skillCoverage = evaluationService.calculateSkillCoverage(teacher, skillBook)
        def skills = skillBook.skills.sort { it.path }

        respond skills, model: [skillBookId: skillBook.id, skillBookTitle: skillBook.title, evaluationCount: evaluationCount, skillCoverage: skillCoverage]

    }

    def sortSkills() {
        def skillBookId = params.int('skillBookId')
        SkillBook skillBook = SkillBook.get(skillBookId)
        def sortedSkills = skillBook.skills.sort { it.path }
        render sortedSkills as JSON
    }
}
