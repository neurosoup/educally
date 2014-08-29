package educally

class Skill {

    static constraints = {
        path nullable: true
    }

    static belongsTo = [level: SkillLevel]

    String title
    String path

}
