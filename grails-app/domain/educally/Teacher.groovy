package educally

class Teacher {

    static constraints = {

    }

    static belongsTo = [account: Account]

    static hasMany = [pupils: Pupil, skillBooks: SkillBook, evaluations: Evaluation]

    List<String> evaluationTags = []
    List<String> pupilTags = []

}
