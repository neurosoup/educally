package educally

import org.joda.time.LocalDateTime

class EvaluatedSkill {

    static constraints = {
        value nullable: true
    }

    static belongsTo = [skill: Skill]

    BigDecimal value
    Boolean missed = false
    LocalDateTime dateTime

}
