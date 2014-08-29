package educally

class Evaluation {

    static constraints = {
        notationValue nullable: true
    }

    static belongsTo = [pupil: Pupil, teacher: Teacher]

    static hasMany = [skills: TeacherSkill]

    String title
    NotationValue notationValue

}
