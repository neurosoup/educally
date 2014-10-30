package educally

class Skill {

    static constraints = {
        name nullable: true //leaf
        path nullable: true //root
        basedOn nullable: true
    }

    static mapping = {
        path index: true
    }

    static embedded = ['stats']

    static belongsTo = [skillBook: SkillBook]

    static hasMany = [ratings: Rating]

    static ratedLeaves = where { !name && !ratings.isEmpty() }

    static leaves = where { !name }

    String title
    String name
    String path
    Skill basedOn
    SkillStats stats

}
