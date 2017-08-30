package demo

class Player {
    String name

    static hasMany = [library: Game]

    static constraints = {
        name blank: false
    }
}