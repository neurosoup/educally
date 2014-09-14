package educally

import grails.transaction.Transactional
import org.joda.time.LocalDateTime

@Transactional
class EvaluationService {

    def Evaluation createEvaluationForPupil(List<String> tags, Pupil pupil, String title, NotationSystem preferredNotationSystem, Skill skill, BigDecimal value = null) {

        def evaluation = new Evaluation(tags: tags, title: title).addToPreferredNotationSystems(preferredNotationSystem)
        evaluation.values.add(new EvaluatedSkill(skill: skill, value: value, missed: !value, dateTime: LocalDateTime.now()))
        skill.addToEvaluations(evaluation)
        pupil.addToEvaluations(evaluation)
        pupil.save()

        evaluation
    }

    def addEvaluationValue(Evaluation evaluation, Skill skill, BigDecimal value) {
        def toto = []
        toto.each {}
        evaluation.values.add(new EvaluatedSkill(skill: skill, value: value, missed: !value, dateTime: LocalDateTime.now()))
        evaluation.save()
    }

    def BigDecimal calculateSkillCoverageByTeacherAndSchoolYear(Teacher teacher, SchoolYear schoolYear) {

        def pupils = teacher.pupils
        def skills = teacher.skillBooks.find { it.schoolYear == schoolYear }?.skills
        if (skills) {
            def evaluatedSkills = skills.findAll {
                it.any {
                    it.evaluations.any {
                        pupils.contains(it.pupil)
                    }
                }
            }
            evaluatedSkills.count / skills.count
        } else {
            0.0
        }

    }

}

