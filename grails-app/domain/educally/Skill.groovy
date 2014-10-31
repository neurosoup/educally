package educally

class Skill {

    static constraints = {
        ratings nullable: true
        stats nullable: true

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

    static leaves = where { name == null }
    static ratedLeaves
    {
        leaves && ratings != null && ratings.size() > 0
    }

    String title
    String name
    String path
    Skill basedOn
    SkillStats stats

}
