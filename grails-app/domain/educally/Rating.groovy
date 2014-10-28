package educally

import org.joda.time.LocalDateTime

class Rating {

    static constraints = {

    }

    static mapping = {

    }

    BigDecimal value
    Boolean missed = false
    Boolean awaiting = true
    LocalDateTime dateTime

}
