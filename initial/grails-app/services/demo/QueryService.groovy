package demo

import grails.gorm.transactions.ReadOnly
import org.apache.commons.lang.StringUtils

@ReadOnly
class QueryService {

    // tag::findByProp[]
    Game queryGame(String name) {
        Game.all.find {
            it.name == name
        }
    }
    // end::findByProp[]

    // tag::findAllByProp[]
    List<Game> queryGamesWithAverageDuration(Integer averageDuration) {
        Game.all.findAll {
            it.averageDuration == averageDuration
        }
    }
    // end::findAllByProp[]

    // tag::findAllNotEqual[]
    List<Game> queryGamesNotConsideredStrategy() {
        Game.all.findAll {
            it.strategy != true
        }
    }
    // end::findAllNotEqual[]

    // tag::findAllByLessThan[]
    List<Game> queryGamesExpectedShorterThan(Integer duration) {
        Game.all.findAll {
            it.averageDuration < duration
        }
    }
    // end::findAllByLessThan[]

    // tag::findAllByGreaterThan[]
    List<Game> queryGamesRatedMoreThan(BigDecimal rating) {
        Game.all.findAll {
            it.rating > rating
        }
    }
    // end::findAllByGreaterThan[]

    // tag::countByGreaterThan[]
    int queryHowManyGamesRatedAtLeast(BigDecimal rating) {
        Game.all.count {
            it.rating >= rating
        }
    }
    // end::countByGreaterThan[]

    // tag::findByBetween[]
    List<Game> queryMatchesPlayedBetweenDates(Date startDate, Date finishDate) {
        Match.all.findAll {
            startDate <= it.started && it.started <= finishDate
        }
    }
    // end::findByBetween[]

    // tag::findByRange[]
    int queryHowManyScoresWithinRange(Range range) {
        Score.all.count {
            it.score in range
        }
    }
    // end::findByRange[]

    // tag::findByLike[]
    List<Game> queryPlayersWithLastName(String lastName) {
        Player.all.findAll {
            it.name.endsWith " ${lastName}"
        }
    }
    // end::findByLike[]

    // tag::findByIlike[]
    List<Game> queryMechanicsContaining(String text) {
        Mechanic.all.findAll {
            StringUtils.containsIgnoreCase it.name, text
        }
    }
    // end::findByIlike[]

    // tag::findByRlike[]
    List<Game> queryGamesMatching(String pattern) {
        Game.all.findAll {
            it.name ==~ pattern
        }
    }
    // end::findByRlike[]

    // tag::findByNull[]
    int queryHowManyMatchesInProgress() {
        Match.all.count {
            it.finished == null
        }
    }
    // end::findByNull[]

    // tag::findByNotNull[]
    int queryHowManyMatchesCompleted() {
        Match.all.count {
            it.finished != null
        }
    }
    // end::findByNotNull[]

    // tag::findStringInList[]
    List<Game> queryGamesForNames(List<String> names) {
        Game.all.findAll {
            it.name in names
        }
    }
    // end::findStringInList[]

    // tag::findDomainInList[]
    List<Game> queryMatchesForGames(List<Game> games) {
        Match.all.findAll {
            it.game in games
        }
    }
    // end::findDomainInList[]

    // tag::findNotInList[]
    List<Game> queryGamesOtherThan(List<Game> games) {
        Game.all.findAll {
            !(it in games)
        }
    }
    // end::findNotInList[]

    // tag::findCombinatorAnd[]
    int queryHowManyGamesSupportPlayerCount(Integer playerCount) {
        Game.all.count {
            it.minPlayers <= playerCount && playerCount <= it.maxPlayers
        }
    }
    // end::findCombinatorAnd[]

    // tag::findCombinatorAnd2[]
    List<Game> queryGamesSupportExactPlayerCount(Integer playerCount) {
        Game.all.findAll {
            it.minPlayers == playerCount && it.maxPlayers == playerCount
        }
    }
    // end::findCombinatorAnd2[]

    // tag::findCombinatorOr[]
    List<Game> queryGamesConsideredFamilyOrParty() {
        Game.all.findAll {
            it.family || it.party
        }
    }
    // end::findCombinatorOr[]

}
