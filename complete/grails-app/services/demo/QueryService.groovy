package demo

class QueryService {

    def findGamesRatedAtLeast(minRating) {
        // Game.list().findAll { it.rating >= minRating }

        Game.findAllByRatingGreaterThanEquals(minRating)
    }

    def findGamesForPlayerCount(int playerCount) {
        // Game.list().findAll { it.minPlayers <= playerCount && playerCount <= it.maxPlayers }

        Game.findAllByMinPlayersLessThanEqualsAndMaxPlayersGreaterThanEquals(playerCount, playerCount)
    }

    def findMatchesInProgress() {
        // Match.list().findAll { !it.finished }

        Match.findAllByFinishedIsNull()
    }

    def findUniqueGamesPlayed() {
        Match.list()*.game.unique()

        // TODO: Not possible with dynamic finder?
    }

}
