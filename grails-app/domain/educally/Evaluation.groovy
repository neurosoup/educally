package educally

class Evaluation {

    static constraints = {
        ratings nullable: true
    }

    static hasMany = [preferredNotationSystems: NotationSystem, ratings: Rating]

    static embedded = ['stats']

    String title
    List<String> tags = []
    EvaluationStats stats

}
