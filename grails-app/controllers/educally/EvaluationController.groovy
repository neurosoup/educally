package educally

import grails.converters.JSON
import grails.converters.XML

class EvaluationController {

    static scaffold = true

    static allowedMethods = [sortSkills: 'POST']

    def teacherService

    def manage() {

        Teacher teacher = teacherService.currentTeacher
        SkillBook skillBook = SkillBook.get(params.int('skillBookId'))

        def evaluations = teacher.pupils.flatten().evaluations.flatten().groupBy { x -> x.values.sort { y -> y.skillId }.skillId }
        def sortedSkills = skillBook.skills.sort { it.path }

        BigDecimal skillCount = sortedSkills.findAll { !it.name }.size()
        BigDecimal evaluatedCount = evaluations.values().flatten().size()
        def skillCoverage = skillCount > 0 ? Math.round(evaluatedCount / skillCount * 100) : 0

        def skillModel = []

        sortedSkills.each { x ->
            def skill = new Expando()
            skill.stats = [:]
            skill.domainInstance = x
            skillModel.add(skill)
        }

        for (entry in evaluations) {
            for (skillId in entry.key) {
                def skill = skillModel.find { it.domainInstance.id == skillId }
                skill.evaluations = []
                skill.linkedSkills = []
                for (evaluation in entry.value) {
                    skill.evaluations.add(evaluation)
                    for (value in evaluation.values) {
                        def s = sortedSkills.find { it.id == value.skill.id }
                        def linkedSkill = new Expando()
                        if (s.id != skillId && !skill.linkedSkills.any { y -> y.id == s.id }) {
                            linkedSkill.id = s.id
                            linkedSkill.title = s.title
                            skill.linkedSkills.add(linkedSkill)
                        }
                    }
                }
                skill.stats.put('count', skill.evaluations.size())
            }
        }

        respond skillModel, model: [skillBook: skillBook, skillCoverage: skillCoverage]
    }

}
