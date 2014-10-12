package educally

import grails.converters.JSON
import grails.converters.XML

class EvaluationController {

    static scaffold = true

    def teacherService

    def index() {

        def model = new Expando()
        if (params.int('localStorage') == 0) {

            Teacher teacher = teacherService.currentTeacher
            SkillBook skillBook = SkillBook.get(params.int('skillBookId'))
            def evaluations = teacher.pupils.flatten().evaluations.flatten().groupBy { x -> x.values.sort { y -> y.skillId }.skillId }
            def sortedSkills = skillBook.skills.sort { it.path }
            BigDecimal skillCount = sortedSkills.findAll { !it.name }.size()
            BigDecimal evaluatedCount = evaluations.values().flatten().size()
            def skillCoverage = skillCount > 0 ? Math.round(evaluatedCount / skillCount * 100) : 0

            model.skills = []
            model.stats = [:]
            model.skillBook = skillBook
            model.stats.put('total', skillCount)
            model.stats.put('count', evaluatedCount)
            model.stats.put('coverage', skillCoverage)

            sortedSkills.each { x ->
                def skill = new Expando()
                skill.stats = [:]
                skill.domainInstance = x
                model.skills.add(skill)
            }

            for (entry in evaluations) {
                for (skillId in entry.key) {
                    def skill = model.skills.find { it.domainInstance.id == skillId }
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
                    skill.stats.put('evaluationCount', skill.evaluations.size())
                }
            }
        }

        respond model
    }

}
