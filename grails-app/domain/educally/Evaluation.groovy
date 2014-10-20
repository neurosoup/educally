package educally

class Evaluation {

    static constraints = {

    }

    static hasMany = [preferredNotationSystems: NotationSystem]

    static embedded = ['stats']

    String title
    List<String> tags = []
    EvaluationStats stats

}
