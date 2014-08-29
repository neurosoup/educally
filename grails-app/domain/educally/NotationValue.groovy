package educally

class NotationValue {

    static constraints = {
    }

    static belongsTo = [notationSystem: NotationSystem]

    String displayValue
    BigDecimal decimalValue
}
