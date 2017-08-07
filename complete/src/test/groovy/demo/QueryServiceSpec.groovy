package demo

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import org.junit.Before
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(QueryService)
@Mock([Category, Game, Match, Mechanic, Player, Score])
class QueryServiceSpec extends Specification {

    def setup() {
        DemoData.initialize()
    }

    def cleanup() {
    }

    def 'test we have demo data'() {
        expect:
        Category.count() == 33
        Mechanic.count() == 29
        Game.count() == 25
        Match.count() == 31
        Player.count() == 16
    }

    def 'test how many games have at least X rating'() {
        when:
        def games = service.findGamesRatedAtLeast(rating)

        then:
        expected == games.size()

        where:
        rating | expected
        2.0    | 25
        4.0    | 24
        6.0    | 8
        8.0    | 2
    }

    def 'test how many games support N players'() {
        when:
        def games = service.findGamesForPlayerCount(playerCount)

        then:
        expected == games.size()

        where:
        playerCount | expected
        1           | 3
        2           | 22
        3           | 23
        4           | 23
        5           | 12
        6           | 8
        7           | 3
        8           | 2
        9           | 1
    }

    def 'test how many unique games were played'() {
        expect:
        Match.count() == 31     // total matches played

        when:
        def uniqueGamesPlayed = service.findUniqueGamesPlayed()

        then:
        uniqueGamesPlayed.size() == 16
    }

    def 'test how many games are in progress'() {
        expect:
        service.findMatchesInProgress().size() == 9
    }



}
