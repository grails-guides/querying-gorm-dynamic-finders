package demo

class Game {
    String name
    Integer minPlayers
    Integer maxPlayers
    Integer aveDuration     // in minutes
    BigDecimal rating       // from 0.0 to 10.0
    Boolean family = false
    Boolean party = false
    Boolean thematic = false
    Boolean strategy = false

    static hasMany = [categories: Category, mechanics: Mechanic]

    static constraints = {
        name blank: false
        minPlayers min: 1
        maxPlayers validator: { val, obj -> val >= obj.minPlayers }
        aveDuration min: 1
        rating min: 0.0, max: 10.0
    }

}