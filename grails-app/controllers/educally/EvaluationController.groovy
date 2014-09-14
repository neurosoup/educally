package educally


class EvaluationController {

    static scaffold = true

    def evaluationService
    def teacherService

    def manage() {
        Teacher teacher = teacherService.currentTeacher
        SchoolYear schoolYear = SchoolYear.get(params.schoolYearId)

        Evaluation.async.task {
            [evaluationList: list(params), count: count()]
        }.then { result ->
            respond result.evaluationList,
                    model: [evaluationCount: result.count, skillCoverage: evaluationService.calculateSkillCoverageByTeacherAndSchoolYear(teacher, schoolYear)]
        }
    }
}
