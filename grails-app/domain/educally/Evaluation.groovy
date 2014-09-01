package educally

class Evaluation {

    static constraints = {
    }

    static belongsTo = [pupil: Pupil]

    static hasMany = [evaluatedSkills: EvaluatedSkill]

    static hasOne = [preferredNotationSystem: NotationSystem]

    String title
    List<String> tags = []

}
