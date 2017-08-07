package demo

class Game {
    String name
    Integer minPlayers
    Integer maxPlayers
    Integer aveDuration     // in minutes
    BigDecimal rating       // from 0.0 to 10.0

    static hasMany = [categories: Category, mechanics: Mechanic]

    static constraints = {
        name blank: false
        minPlayers min: 1
        maxPlayers min: 1
        aveDuration min: 1
        rating min: 0.0, max: 10.0
    }

}