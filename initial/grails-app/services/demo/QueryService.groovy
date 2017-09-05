package demo

import org.apache.commons.lang.StringUtils

class QueryService {

    // tag::findByProp[]
    def queryGame(String name) {
        Game.all.find {
            it.name == name
        }
    }
    // end::findByProp[]

    // tag::findAllByProp[]
    def queryGamesWithAverageDuration(Integer averageDuration) {
        Game.all.findAll {
            it.averageDuration == averageDuration
        }
    }
    // end::findAllByProp[]

    // tag::findAllNotEqual[]
    def queryGamesNotConsideredStrategy() {
        Game.all.findAll {
            it.strategy != true
        }
    }
    // end::findAllNotEqual[]

    // tag::findAllByLessThan[]
    def queryGamesExpectedShorterThan(Integer duration) {
        Game.all.findAll {
            it.averageDuration < duration
        }
    }
    // end::findAllByLessThan[]

    // tag::findAllByGreaterThan[]
    def queryGamesRatedMoreThan(BigDecimal rating) {
        Game.all.findAll {
            it.rating > rating
        }
    }
    // end::findAllByGreaterThan[]

    // tag::countByGreaterThan[]
    def queryHowManyGamesRatedAtLeast(BigDecimal rating) {
        Game.all.count {
            it.rating >= rating
        }
    }
    // end::countByGreaterThan[]

    // tag::findByBetween[]
    def queryMatchesPlayedBetweenDates(Date startDate, Date finishDate) {
        Match.all.findAll {
            startDate <= it.started && it.started <= finishDate
        }
    }
    // end::findByBetween[]

    // tag::findByRange[]
    def queryHowManyScoresWithinRange(Range range) {
        Score.all.count {
            it.score in range
        }
    }
    // end::findByRange[]

    // tag::findByLike[]
    def queryPlayersWithLastName(String lastName) {
        Player.all.findAll {
            it.name.endsWith " ${lastName}"
        }
    }
    // end::findByLike[]

    // tag::findByIlike[]
    def queryMechanicsContaining(String text) {
        Mechanic.all.findAll {
            StringUtils.containsIgnoreCase it.name, text
        }
    }
    // end::findByIlike[]

    // tag::findByRlike[]
    def queryGamesMatching(String pattern) {
        Game.all.findAll {
            it.name ==~ pattern
        }
    }
    // end::findByRlike[]

    // tag::findByNull[]
    def queryHowManyMatchesInProgress() {
        Match.all.count {
            it.finished == null
        }
    }
    // end::findByNull[]

    // tag::findByNotNull[]
    def queryHowManyMatchesCompleted() {
        Match.all.count {
            it.finished != null
        }
    }
    // end::findByNotNull[]

    // tag::findStringInList[]
    def queryGamesForNames(List<String> names) {
        Game.all.findAll {
            it.name in names
        }
    }
    // end::findStringInList[]

    // tag::findDomainInList[]
    def queryMatchesForGames(List<Game> games) {
        Match.all.findAll {
            it.game in games
        }
    }
    // end::findDomainInList[]

    // tag::findNotInList[]
    def queryGamesOtherThan(List<Game> games) {
        Game.all.findAll {
            !(it in games)
        }
    }
    // end::findNotInList[]

    // tag::findCombinatorAnd[]
    def queryHowManyGamesSupportPlayerCount(Integer playerCount) {
        Game.all.count {
            it.minPlayers <= playerCount && playerCount <= it.maxPlayers
        }
    }
    // end::findCombinatorAnd[]

    // tag::findCombinatorAnd2[]
    def queryGamesSupportExactPlayerCount(Integer playerCount) {
        Game.all.findAll {
            it.minPlayers == playerCount && it.maxPlayers == playerCount
        }
    }
    // end::findCombinatorAnd2[]

    // tag::findCombinatorOr[]
    def queryGamesConsideredFamilyOrParty() {
        Game.all.findAll {
            it.family || it.party
        }
    }
    // end::findCombinatorOr[]

}
