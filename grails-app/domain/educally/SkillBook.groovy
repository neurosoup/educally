package educally

class SkillBook {

    static constraints = {
        description nullable: true
    }

    static hasMany = [skills: Skill]

    static hasOne = [schoolYear: SchoolYear]

    String title
    String description

}
