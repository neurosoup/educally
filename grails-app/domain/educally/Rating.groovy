package educally

import org.joda.time.LocalDateTime

class Rating {

    static constraints = {

    }

    static mapping = {
        //index(["dateTime": 1], [unique: true])
    }

    //static belongsTo = [pupil: Pupil]

    BigDecimal value
    Boolean missed = false
    LocalDateTime dateTime
    //Skill skill
    //Evaluation evaluation

}
