package educally

import org.joda.time.LocalDate

class Pupil {

    static constraints = {

    }

    static hasMany = [evaluations: Evaluation]

    String firstName
    String lastName
    LocalDate birthDay
    List<String> tags = []

}