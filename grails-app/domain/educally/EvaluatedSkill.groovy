package educally

class EvaluatedSkill {

    static constraints = {
        value nullable: true
    }

    static belongsTo = [evaluationSkill: EvaluationSkill]

    BigDecimal value
    Boolean missed = false
}
