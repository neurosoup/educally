package educally

class Classroom {

    static constraints = {
    }

    static hasMany = [pupils: Pupil, teachers: Teacher]

    String name
}
