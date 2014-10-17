package educally

class Evaluation {

    static constraints = {

    }

    static hasMany = [preferredNotationSystems: NotationSystem]

    String title
    List<String> tags = []

}
