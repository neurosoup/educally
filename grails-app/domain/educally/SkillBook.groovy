package educally

class SkillBook {

    static constraints = {
        description nullable: true
    }

    static hasMany = [skills: Skill]

    String title
    String description
    SchoolYear schoolYear

}
