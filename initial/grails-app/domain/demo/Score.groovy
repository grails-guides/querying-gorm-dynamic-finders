package demo

class Score {
    Player player
    Integer score

    static belongsTo = [match: Match]

    static constraints = {
        score nullable: true, min: 0, max: 100
    }

}