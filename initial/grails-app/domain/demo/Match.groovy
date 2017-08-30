package demo

class Match {
    Game game
    Date started
    Date finished

    static hasMany = [scores: Score]

    static constraints = {
        finished nullable: true
    }
}