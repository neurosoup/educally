package educally

class Skill {

    static constraints = {
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

    String title
    String name
    String path
    Skill basedOn
    SkillStats stats

    def getChildren() {
        skillBook.skills.findAll { it.path == /^,${this.name},/ }
    }

}
