package educally

import org.joda.time.LocalDateTime

class Rating {

    static constraints = {

    }

    static mapping = {
        compoundIndex skill: 1, pupil: 1, evaluation: 1
    }

    static nonZero = where { value > 0 }
    static zero = where { value == 0 }

    BigDecimal value
    Boolean missed = false
    LocalDateTime dateTime

    Skill skill
    Pupil pupil
    Evaluation evaluation

}
