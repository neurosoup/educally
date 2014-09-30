package educally

import org.joda.time.LocalDateTime

class EvaluatedSkill {

    static constraints = {
        value nullable: true
    }

    static belongsTo = [evaluation: Evaluation]

    BigDecimal value
    Boolean missed = false
    LocalDateTime dateTime
    Skill skill

}
