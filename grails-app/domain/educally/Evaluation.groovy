package educally

class Evaluation {

    static constraints = {
        notationValue nullable: true
    }

    static belongsTo = [pupil: Pupil, teacher: Teacher]

    static hasMany = [skills: TeacherSkill]

    public Evaluation() {
        missing = false
    }

    String title
    NotationValue notationValue
    Boolean missing
    List<String> tags

}
