package educally

class SkillBook {

    static constraints = {
        description nullable: true
        stats nullable: true
    }

    static hasMany = [skills: Skill]

    static embedded = ['stats']

    String title
    String description
    SchoolYear schoolYear
    SkillBookStats stats

    def getOrderedSkills() {
        this.skills.sort { it.path }
    }

    def getRoots() {
        this.skills.findAll { it.path == null }
    }

}
