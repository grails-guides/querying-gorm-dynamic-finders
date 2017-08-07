package demo

import grails.transaction.Transactional

@Transactional
class QueryService {

    def findGamesRatedAtLeast(minRating) {
//1        Game.all.findAll { it.rating >= minRating }
//2        Game.findAllByRatingGreaterThanEquals(minRating)

        Game.where {
            rating >= minRating
        }.list()
    }

    def findGamesForPlayerCount(int playerCount) {
//1        Game.all.findAll { it.minPlayers <= playerCount && playerCount <= it.maxPlayers }
//2        Game.findAllByMinPlayersLessThanEqualsAndMaxPlayersGreaterThanEquals(playerCount, playerCount)

        Game.where {
            minPlayers <= playerCount
            maxPlayers >= playerCount
        }.list()
    }

    def findMatchesInProgress() {
//1        Match.all.findAll { !it.finished }
//2        Match.findAllByFinishedIsNull()

        Match.where {
            finished == null
        }.list()
    }

    def findUniqueGamesPlayed() {
//1        Match.all*.game.unique()

        Match.where {
            projections {
                distinct 'game'
            }
        }.list()
    }

}
