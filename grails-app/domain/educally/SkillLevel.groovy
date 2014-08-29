package educally

class SkillLevel {

    static constraints = {
        description nullable: true
    }

    static hasMany = [skills: Skill]

    String title
    String revision
    String description

}
