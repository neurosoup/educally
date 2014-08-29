package educally

import org.joda.time.DateTime

class Pupil {

    static constraints = {

    }

    static belongsTo = [teacher: Teacher]

    static hasMany = [evaluations: Evaluation]

    String firstName
    String lastName
    DateTime birthDay

    List<String> tags

}