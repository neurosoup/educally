package educally

class SkillBook {

    static constraints = {
        description nullable: true
        teacher nullable: true
    }

    static hasMany = [skills: Skill]

    static belongsTo = [teacher: Teacher]

    String title
    String reference
    String description

    Teacher teacher

}
