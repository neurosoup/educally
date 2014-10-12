package educally

import grails.plugin.cache.Cacheable
import grails.transaction.Transactional
import org.joda.time.LocalDateTime

@Transactional
class EvaluationService {

    def Evaluation createEvaluationForPupil(List<String> tags, Pupil pupil, String title, NotationSystem preferredNotationSystem, Skill skill, BigDecimal value = null) {

        def evaluation = new Evaluation(tags: tags, title: title).addToPreferredNotationSystems(preferredNotationSystem)
        evaluation.addToValues(new EvaluatedSkill(skill: skill, value: value, missed: !value, dateTime: LocalDateTime.now()))
        pupil.addToEvaluations(evaluation)
        pupil.save()

        evaluation
    }

    def addEvaluationValue(Evaluation evaluation, Skill skill, BigDecimal value) {
        evaluation.addToValues(new EvaluatedSkill(skill: skill, value: value, missed: !value, dateTime: LocalDateTime.now()))
        evaluation.save()
    }

    def Integer calculateSkillCoverage(Teacher teacher, SkillBook skillBook) {

        BigDecimal skillCount = Skill.countBySkillBook(skillBook)
        BigDecimal evaluatedCount = teacher.pupils.evaluations.values.skill.flatten().collect {
            it.id
        }.unique().size().toBigDecimal()

        if (skillCount > 0) {
            Math.round(evaluatedCount / skillCount * 100)
        } else {
            0
        }

    }

    @Cacheable(value = 'skillsAndEvaluationsByTeacherAndSkillBook', key = "{ #teacher.id, #skillBook.id }")
    def Expando GetSkillsAndEvaluationsByTeacherAndSkillBook(Teacher teacher, SkillBook skillBook) {
        def evaluations = teacher.pupils.flatten().evaluations.flatten().groupBy { x -> x.values.sort { y -> y.skillId }.skillId }
        def sortedSkills = skillBook.skills.sort { it.path }
        BigDecimal skillCount = sortedSkills.findAll { !it.name }.size()
        BigDecimal evaluatedCount = evaluations.values().flatten().size()
        def skillCoverage = skillCount > 0 ? Math.round(evaluatedCount / skillCount * 100) : 0

        def model = new Expando()
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
        model
    }

}

