package educally

class Skill {

    static constraints = {
        name nullable: true //leaf
        path nullable: true //root
        basedOn nullable: true
    }

    static belongsTo = [skillBook: SkillBook]

    String title
    String name
    String path
    Skill basedOn

}
