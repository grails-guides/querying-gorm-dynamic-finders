package demo

import org.apache.commons.lang.StringUtils

class QueryService {

    def queryGame(String name) {
        Game.list().find {
            it.name == name
        }

        //Game.findByName(name)
    }

    def queryGamesWithAverageDuration(Integer averageDuration) {
        Game.list().findAll {
            it.aveDuration == averageDuration
        }

        //Game.findAllByAveDuration(averageDuration)
    }

    def queryGamesExpectedShorterThan(Integer duration) {
        Game.list().findAll {
            it.aveDuration <= duration
        }

        //Game.findAllByAveDurationLessThanEquals(duration)
    }

    def queryGamesRatedMoreThan(BigDecimal rating) {
        Game.list().findAll {
            it.rating > rating
        }

        //Game.findAllByRatingGreaterThan(rating)
    }

    def queryHowManyGamesRatedAtLeast(BigDecimal rating) {
        Game.list().count {
            it.rating >= rating
        }

        //Game.countByRatingGreaterThanEquals(rating)
    }

    def queryGamesWithMinPlayersOtherThanTwo() {
        Game.list().findAll {
            it.minPlayers != 2
        }

        //Game.findAllByMinPlayersNotEqual(2)
    }

    def queryMatchesPlayedBetweenDates(Date startDate, Date finishDate) {
        Match.list().findAll {
            startDate <= it.started && it.started <= finishDate
        }

        //Match.findAllByStartedBetween(startDate, finishDate)
    }

    def queryPlayersWithLastName(String lastName) {
        Player.list().findAll {
            it.name.endsWith " ${lastName}"
        }

        //Player.findAllByNameLike("% ${lastName}")
    }

    def queryHowManyScoresWithinRange(Range range) {
        Score.list().count {
            it.score in range
        }

        //Score.countByScoreInRange(range)
    }

    def queryMechanicsContaining(String text) {
        Mechanic.list().findAll {
            StringUtils.containsIgnoreCase it.name, text
        }

        //Mechanic.findAllByNameIlike("%${text}%")
    }

    def queryGamesMatching(String pattern) {
        Game.list().findAll {
            it.name ==~ pattern
        }

        // Game.findAllByNameRlike(pattern)     // Rlike: limited to MySQL/Oracle
    }

    def queryHowManyMatchesInProgress() {
        Match.list().count {
            it.finished == null
        }

        //Match.countByFinishedIsNull()
    }

    def queryHowManyMatchesCompleted() {
        Match.list().count {
            it.finished != null
        }

        //Match.countByFinishedIsNotNull()
    }

    def queryHowManyGamesSupportPlayerCount(Integer playerCount) {
        Game.list().count {
            it.minPlayers <= playerCount && playerCount <= it.maxPlayers
        }

        //Game.countByMinPlayersLessThanEqualsAndMaxPlayersGreaterThanEquals(playerCount, playerCount)
    }

    def queryGamesSupportExactPlayerCount(Integer playerCount) {
        Game.list().findAll {
            it.minPlayers == playerCount && it.maxPlayers == playerCount
        }

        //Game.findAllByMinPlayersAndMaxPlayers(playerCount, playerCount)
    }

    def queryGamesForNames(List<String> names) {
        Game.list().findAll {
            it.name in names
        }

        //Game.findAllByNameInList(names)
    }

    def queryMatchesForGames(List<Game> games) {
        Match.list().findAll {
            it.game in games
        }

        //Match.findAllByGameInList(games)
    }

    def queryGamesConsideredFamilyOrParty() {
        Game.list().findAll {
            it.family || it.party
        }

        Game.findAllByFamilyOrParty(true, true)
    }

}
