package educally

class Skill {

    static constraints = {
        path nullable: true
    }

    static belongsTo = [level: SkillGroup]

    String title
    String normalizedTitle
    String path

}
