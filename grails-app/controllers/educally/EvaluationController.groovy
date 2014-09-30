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

        def evaluatedSkills = evaluations.values.flatten().groupBy { it.skillId }



        [skills: skills as JSON, evaluatedSkills: evaluatedSkills, skillBookId: skillBook.id, skillBookTitle: skillBook.title, evaluationCount: evaluations.size(), skillCoverage: skillCoverage]

    }

}
