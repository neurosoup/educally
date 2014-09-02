package educally

class SkillGroup {

    static constraints = {
        description nullable: true
    }

    static hasMany = [skills: Skill]

    String title
    String reference
    String description

}
