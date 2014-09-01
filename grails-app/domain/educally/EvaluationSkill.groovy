package educally

class EvaluationSkill {

    static constraints = {
        skill nullable: true
    }

    static belongsTo = [teacher: Teacher]

    String title
    Skill skill
}
