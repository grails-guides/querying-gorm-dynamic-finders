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

	// tag::[]
	def queryMatchesPlayedBetweenDates(Date startDate, Date finishDate) {
        Match.findAllByStartedBetween(startDate, finishDate)
    }
	// end::[]

    // tag::[]
	def queryHowManyScoresWithinRange(Range range) {
        Score.countByScoreInRange(range)
    }
	// end::[]

    // tag::[]
	def queryPlayersWithLastName(String lastName) {
        Player.findAllByNameLike("% ${lastName}")
    }
	// end::[]

    // tag::[]
	def queryMechanicsContaining(String text) {
        Mechanic.findAllByNameIlike("%${text}%")
    }
	// end::[]

    // tag::[]
	def queryGamesMatching(String pattern) {
        Game.findAllByNameRlike(pattern)      // Rlike: not universally supported
    }
	// end::[]

    // tag::[]
	def queryHowManyMatchesInProgress() {
        Match.countByFinishedIsNull()
    }
	// end::[]

    // tag::[]
	def queryHowManyMatchesCompleted() {
        Match.countByFinishedIsNotNull()
    }
	// end::[]

    // tag::[]
	def queryHowManyGamesSupportPlayerCount(Integer playerCount) {
        Game.countByMinPlayersLessThanEqualsAndMaxPlayersGreaterThanEquals(playerCount, playerCount)
    }
	// end::[]

    // tag::[]
	def queryGamesSupportExactPlayerCount(Integer playerCount) {
        Game.findAllByMinPlayersAndMaxPlayers(playerCount, playerCount)
    }
	// end::[]

    // tag::[]
	def queryGamesForNames(List<String> names) {
        Game.findAllByNameInList(names)
    }
	// end::[]

    // tag::[]
	def queryMatchesForGames(List<Game> games) {
        Match.findAllByGameInList(games)
    }
	// end::[]

    // tag::[]
	def queryGamesConsideredFamilyOrParty() {
        Game.findAllByFamilyOrParty(true, true)
    }
	// end::[]

    // tag::[]
	def queryHowManyGamesNotConsideredStrategy() {
        Game.countByStrategyNotEqual(true)
    }
	// end::[]

    // tag::[]
	def queryGamesOtherThan(List<Game> games) {
        Game.findAllByNameNotInList(games*.name)
    }
    // end::[]
}
