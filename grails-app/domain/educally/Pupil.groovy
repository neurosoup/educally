package educally

import org.joda.time.LocalDate

class Pupil {

    static constraints = {
        ratings nullable: true
    }

    static hasMany = [ratings: Rating]

    String firstName
    String lastName
    LocalDate birthDay
    List<String> tags = []


}