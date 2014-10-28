package educally

class Evaluation {

    static constraints = {

    }

    static hasMany = [preferredNotationSystems: NotationSystem, ratings: Rating]

    static embedded = ['stats']

    String title
    List<String> tags = []
    EvaluationStats stats

}
