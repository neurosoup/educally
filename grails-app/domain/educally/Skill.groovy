package educally

class Skill {

    static constraints = {
        stats nullable: true
        name nullable: true //if null then leaf
        path nullable: true //if null then root
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
        skillBook.skills.findAll { it.path =~ /,${this.name},/ }
    }

    def getRoot() {
        if (this.path == null) return this
        def name = (this.path.split(',') - "").first()
        this.skillBook.skills.find { it.name == name }
    }

}
