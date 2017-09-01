package demo

class QueryService {

    // tag::findByName[]
    def queryGame(String name) {
        Game.findByName(name)
    }
    // end::findByName[]

    // tag::findAllByProp[]
    def queryGamesWithAverageDuration(Integer averageDuration) {
        Game.findAllByAverageDuration(averageDuration)
    }
    // end::findAllByProp[]

    // tag::findAllByLessThan[]
    def queryGamesExpectedShorterThan(Integer duration) {
        Game.findAllByAverageDurationLessThan(duration)
    }
    // end::findAllByLessThan[]

    // tag::findAllByGreaterThan[]
    def queryGamesRatedMoreThan(BigDecimal rating) {
        Game.findAllByRatingGreaterThan(rating)
    }
    // end::findAllByGreaterThan[]

    // tag::countByGreaterThan[]
    def queryHowManyGamesRatedAtLeast(BigDecimal rating) {
        Game.countByRatingGreaterThanEquals(rating)
    }
    // end::countByGreaterThan[]

    def queryMatchesPlayedBetweenDates(Date startDate, Date finishDate) {
        Match.findAllByStartedBetween(startDate, finishDate)
    }

    def queryPlayersWithLastName(String lastName) {
        Player.findAllByNameLike("% ${lastName}")
    }

    def queryHowManyScoresWithinRange(Range range) {
        Score.countByScoreInRange(range)
    }

    def queryMechanicsContaining(String text) {
        Mechanic.findAllByNameIlike("%${text}%")
    }

    def queryGamesMatching(String pattern) {
        Game.findAllByNameRlike(pattern)      // Rlike: not universally supported
    }

    def queryHowManyMatchesInProgress() {
        Match.countByFinishedIsNull()
    }

    def queryHowManyMatchesCompleted() {
        Match.countByFinishedIsNotNull()
    }

    def queryHowManyGamesSupportPlayerCount(Integer playerCount) {
        Game.countByMinPlayersLessThanEqualsAndMaxPlayersGreaterThanEquals(playerCount, playerCount)
    }

    def queryGamesSupportExactPlayerCount(Integer playerCount) {
        Game.findAllByMinPlayersAndMaxPlayers(playerCount, playerCount)
    }

    def queryGamesForNames(List<String> names) {
        Game.findAllByNameInList(names)
    }

    def queryMatchesForGames(List<Game> games) {
        Match.findAllByGameInList(games)
    }

    def queryGamesConsideredFamilyOrParty() {
        Game.findAllByFamilyOrParty(true, true)
    }

    def queryHowManyGamesNotConsideredStrategy() {
        Game.countByStrategyNotEqual(true)
    }

    def queryGamesOtherThan(List<Game> games) {
        Game.findAllByNameNotInList(games*.name)
    }

}
