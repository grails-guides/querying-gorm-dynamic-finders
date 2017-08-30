package demo

import org.apache.commons.lang.StringUtils

class QueryService {

    def queryGame(String name) {
        //Game.all.find {
        //    it.name == name
        //}

        Game.findByName(name)
    }

    def queryGamesWithAverageDuration(Integer averageDuration) {
        //Game.all.findAll {
        //    it.aveDuration == averageDuration
        //}

        Game.findAllByAveDuration(averageDuration)
    }

    def queryGamesExpectedShorterThan(Integer duration) {
        //Game.all.findAll {
        //    it.aveDuration <= duration
        //}

        Game.findAllByAveDurationLessThanEquals(duration)
    }

    def queryGamesRatedMoreThan(BigDecimal rating) {
        //Game.all.findAll {
        //    it.rating > rating
        //}

        Game.findAllByRatingGreaterThan(rating)
    }

    def queryHowManyGamesRatedAtLeast(BigDecimal rating) {
        //Game.all.count {
        //    it.rating >= rating
        //}

        Game.countByRatingGreaterThanEquals(rating)
    }

    def queryMatchesPlayedBetweenDates(Date startDate, Date finishDate) {
        //Match.all.findAll {
        //    startDate <= it.started && it.started <= finishDate
        //}

        Match.findAllByStartedBetween(startDate, finishDate)
    }

    def queryPlayersWithLastName(String lastName) {
        //Player.all.findAll {
        //    it.name.endsWith " ${lastName}"
        //}

        Player.findAllByNameLike("% ${lastName}")
    }

    def queryHowManyScoresWithinRange(Range range) {
        //Score.all.count {
        //    it.score in range
        //}

        Score.countByScoreInRange(range)
    }

    def queryMechanicsContaining(String text) {
        //Mechanic.all.findAll {
        //    StringUtils.containsIgnoreCase it.name, text
        //}

        Mechanic.findAllByNameIlike("%${text}%")
    }

    def queryGamesMatching(String pattern) {
        //Game.all.findAll {
        //    it.name ==~ pattern
        //}

        Game.findAllByNameRlike(pattern)      // Rlike: not universally supported
    }

    def queryHowManyMatchesInProgress() {
        //Match.all.count {
        //    it.finished == null
        //}

        Match.countByFinishedIsNull()
    }

    def queryHowManyMatchesCompleted() {
        //Match.all.count {
        //    it.finished != null
        //}

        Match.countByFinishedIsNotNull()
    }

    def queryHowManyGamesSupportPlayerCount(Integer playerCount) {
        //Game.all.count {
        //    it.minPlayers <= playerCount && playerCount <= it.maxPlayers
        //}

        Game.countByMinPlayersLessThanEqualsAndMaxPlayersGreaterThanEquals(playerCount, playerCount)
    }

    def queryGamesSupportExactPlayerCount(Integer playerCount) {
        //Game.all.findAll {
        //    it.minPlayers == playerCount && it.maxPlayers == playerCount
        //}

        Game.findAllByMinPlayersAndMaxPlayers(playerCount, playerCount)
    }

    def queryGamesForNames(List<String> names) {
        //Game.all.findAll {
        //    it.name in names
        //}

        Game.findAllByNameInList(names)
    }

    def queryMatchesForGames(List<Game> games) {
        //Match.all.findAll {
        //    it.game in games
        //}

        Match.findAllByGameInList(games)
    }

    def queryGamesConsideredFamilyOrParty() {
        //Game.all.findAll {
        //    it.family || it.party
        //}

        Game.findAllByFamilyOrParty(true, true)
    }

    def queryHowManyGamesNotConsideredStrategy() {
        //Game.all.count {
        //    !it.strategy
        //}

        Game.countByStrategyNotEqual(true)
    }

    def queryGamesOtherThan(List<Game> games) {
        //Game.all.findAll {
        //    !(it in games)
        //}

        Game.findAllByNameNotInList(games*.name)
    }

}
