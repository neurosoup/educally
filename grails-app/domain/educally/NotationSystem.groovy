package educally

class NotationSystem {

    static constraints = {
        maxValue nullable: true
    }

    static hasMany = [notationValues: NotationValue]

    String title
    String maxValue
}
