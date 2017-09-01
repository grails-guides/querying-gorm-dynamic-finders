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

    // tag::[]
    def queryMatchesPlayedBetweenDates(Date startDate, Date finishDate) {
        Match.all.findAll {
            startDate <= it.started && it.started <= finishDate
        }
    }
    // end::[]

    // tag::[]
    def queryHowManyScoresWithinRange(Range range) {
        Score.all.count {
            it.score in range
        }
    }
    // end::[]

    // tag::[]
    def queryPlayersWithLastName(String lastName) {
        Player.all.findAll {
            it.name.endsWith " ${lastName}"
        }
    }
    // end::[]

    // tag::[]
    def queryMechanicsContaining(String text) {
        Mechanic.all.findAll {
            StringUtils.containsIgnoreCase it.name, text
        }
    }
    // end::[]

    // tag::[]
    def queryGamesMatching(String pattern) {
        Game.all.findAll {
            it.name ==~ pattern
        }
    }
    // end::[]

    // tag::[]
    def queryHowManyMatchesInProgress() {
        Match.all.count {
            it.finished == null
        }
    }
    // end::[]

    // tag::[]
    def queryHowManyMatchesCompleted() {
        Match.all.count {
            it.finished != null
        }
    }
    // end::[]

    // tag::[]
    def queryHowManyGamesSupportPlayerCount(Integer playerCount) {
        Game.all.count {
            it.minPlayers <= playerCount && playerCount <= it.maxPlayers
        }
    }
    // end::[]

    // tag::[]
    def queryGamesSupportExactPlayerCount(Integer playerCount) {
        Game.all.findAll {
            it.minPlayers == playerCount && it.maxPlayers == playerCount
        }
    }
    // end::[]

    // tag::[]
    def queryGamesForNames(List<String> names) {
        Game.all.findAll {
            it.name in names
        }
    }
    // end::[]

    // tag::[]
    def queryMatchesForGames(List<Game> games) {
        Match.all.findAll {
            it.game in games
        }
    }
    // end::[]

    // tag::[]
    def queryGamesConsideredFamilyOrParty() {
        Game.all.findAll {
            it.family || it.party
        }
    }
    // end::[]

    // tag::[]
    def queryHowManyGamesNotConsideredStrategy() {
        Game.all.count {
            !it.strategy
        }
    }
    // end::[]

    // tag::[]
    def queryGamesOtherThan(List<Game> games) {
        Game.all.findAll {
            !(it in games)
        }
    }
    // end::[]

}
