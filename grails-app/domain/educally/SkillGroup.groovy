package educally

class SkillGroup {

    static constraints = {
        description nullable: true
        basedOn nullable: true
    }

    static hasMany = [skills: Skill]

    static hasOne = [basedOn: SkillGroup]

    String title
    String reference
    String description

}
