package educally

class EvaluationController {

    static scaffold = true

    def teacherService
    def evaluationService

    def index() {
        Teacher teacher = teacherService.currentTeacher
        SkillBook skillBook = SkillBook.get(params.int('skillBookId'))
        def model = evaluationService.GetSkillsAndEvaluationsByTeacherAndSkillBook(teacher, skillBook)
        respond model
    }



}
