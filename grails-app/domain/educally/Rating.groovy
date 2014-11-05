package educally

import org.joda.time.LocalDateTime

class Rating {

    static constraints = {

    }

    static mapping = {
        compoundIndex skill: 1, pupil: 1, evaluation: 1
    }

    int scale = 1000
    int scaledValue
    Boolean missed = false
    LocalDateTime dateTime

    Skill skill
    Pupil pupil
    Evaluation evaluation

    def BigDecimal getValue() {
        scaledValue / scale
    }

    def setValue(BigDecimal value) {
        scaledValue = value * scale
    }

}
