package educally

class EvaluatedSkill {

    static constraints = {
        officialSkill nullable: true
    }

    static belongsTo = [teacher: Teacher]

    String title
    OfficialSkill officialSkill
}
