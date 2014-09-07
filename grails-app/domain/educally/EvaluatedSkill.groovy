package educally

class EvaluatedSkill {

    static constraints = {
        value nullable: true
    }

    static hasOne = [skill: Skill]

    BigDecimal value
    Boolean missed = false

}
