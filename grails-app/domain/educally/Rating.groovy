package educally

import org.joda.time.LocalDateTime

class Rating {

    static constraints = {

    }

    static mapping = {
        compoundIndex skill:1, pupil:1, evaluation:1
    }

    BigDecimal value
    Boolean missed = false
    Boolean awaiting = true
    LocalDateTime dateTime

    Skill skill
    Pupil pupil
    Evaluation evaluation

}
