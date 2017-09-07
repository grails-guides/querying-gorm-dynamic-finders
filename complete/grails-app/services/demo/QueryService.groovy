package demo

class QueryService {

    // tag::findByProp[]
    def queryGame(String name) {
        Game.findByName(name)
    }
    // end::findByProp[]

    // tag::findAllByProp[]
    def queryGamesWithAverageDuration(Integer averageDuration) {
        Game.findAllByAverageDuration(averageDuration)
    }
    // end::findAllByProp[]

    // tag::findAllNotEqual[]
    def queryGamesNotConsideredStrategy() {
        // General case: using the NotEqual comparator.
        Game.findAllByStrategyNotEqual(true)    // <1>

        // Special case for booleans: using Boolean exceptional form.
        Game.findAllNotStrategy()    // <2>
    }
    // end::findAllNotEqual[]

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

	// tag::findByBetween[]
    def queryMatchesPlayedBetweenDates(Date startDate, Date finishDate) {
        Match.findAllByStartedBetween(startDate, finishDate)
    }
	// end::findByBetween[]

    // tag::findByRange[]
    def queryHowManyScoresWithinRange(Range range) {
        Score.countByScoreInRange(range)
    }
	// end::findByRange[]

    // tag::findByLike[]
    def queryPlayersWithLastName(String lastName) {
        Player.findAllByNameLike("% ${lastName}")
    }
	// end::findByLike[]

    // tag::findByIlike[]
    def queryMechanicsContaining(String text) {
        Mechanic.findAllByNameIlike("%${text}%")
    }
	// end::findByIlike[]

    // tag::findByRlike[]
    def queryGamesMatching(String pattern) {
        Game.findAllByNameRlike(pattern)      // Rlike: not universally supported
    }
	// end::findByRlike[]

    // tag::findByNull[]
    def queryHowManyMatchesInProgress() {
        Match.countByFinishedIsNull()
    }
	// end::findByNull[]

    // tag::findByNotNull[]
    def queryHowManyMatchesCompleted() {
        Match.countByFinishedIsNotNull()
    }
	// end::findByNotNull[]

    // tag::findStringInList[]
    def queryGamesForNames(List<String> names) {
        Game.findAllByNameInList(names)
    }
    // end::findStringInList[]

    // tag::findDomainInList[]
    def queryMatchesForGames(List<Game> games) {
        Match.findAllByGameInList(games)
    }
    // end::findDomainInList[]

    // tag::findNotInList[]
    def queryGamesOtherThan(List<Game> games) {
        Game.findAllByNameNotInList(games*.name)
    }
    // end::findNotInList[]

    // tag::findCombinatorAnd[]
    def queryHowManyGamesSupportPlayerCount(Integer playerCount) {
        Game.countByMinPlayersLessThanEqualsAndMaxPlayersGreaterThanEquals(playerCount, playerCount)
    }
	// end::findCombinatorAnd[]

    // tag::findCombinatorAnd2[]
    def queryGamesSupportExactPlayerCount(Integer playerCount) {
        Game.findAllByMinPlayersAndMaxPlayers(playerCount, playerCount)
    }
	// end::findCombinatorAnd2[]

    // tag::findCombinatorOr[]
    def queryGamesConsideredFamilyOrParty() {
        Game.findAllByFamilyOrParty(true, true)
    }
	// end::findCombinatorOr[]

}
