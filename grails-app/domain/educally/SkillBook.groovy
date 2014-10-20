package educally

class SkillBook {

    static constraints = {
        description nullable: true
    }

    static hasMany = [skills: Skill]

    static embedded = ['stats']

    String title
    String description
    SchoolYear schoolYear
    SkillBookStats stats

}
