package educally

class Teacher {

    static constraints = {

    }

    static belongsTo = [account: Account]

    static hasMany = [evaluationSkills: EvaluationSkill, pupils: Pupil]

    List<String> evaluationTags = []
    List<String> pupilTags = []

}
