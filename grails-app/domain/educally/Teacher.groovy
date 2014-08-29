package educally

class Teacher {

    static constraints = {

    }

    static belongsTo = [account: Account]

    static hasMany = [pupils: Pupil, evaluations: Evaluation]

}
