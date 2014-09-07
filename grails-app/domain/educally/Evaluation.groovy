package educally

class Evaluation {

    static constraints = {

    }

    static belongsTo = [pupil: Pupil]

    static hasMany = [preferredNotationSystems: NotationSystem]

    static embedded = ['values']

    String title
    List<String> tags = []
    List<EvaluatedSkill> values = []

}
