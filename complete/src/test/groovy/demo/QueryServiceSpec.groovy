package demo

import grails.testing.gorm.DataTest
import grails.testing.services.ServiceUnitTest
import spock.lang.Specification
import spock.lang.Unroll

import java.time.LocalDate
import java.time.ZoneId

class QueryServiceSpec extends Specification implements ServiceUnitTest<QueryService>, DataTest {

    void setupSpec() {
        mockDomains Category, Game, Match, Mechanic, Player, Score
    }

    def setup() {
        DemoData.initialize()
    }

    // Take two collections and compare for equality as sets.
    private static areEqualSets(a, b) {
        (a as Set) == (b as Set)
    }

    private static getOwnedGames() {
        Player.list().inject([].toSet()) { owned, player ->
            owned.addAll player.library
            owned
        } as List
    }

    def 'test that demo data is loaded'() {
        expect:
        Category.count() == 33
        Mechanic.count() == 29
        Game.count() == 25
        Match.count() == 31
        Player.count() == 16
    }

    @Unroll
    def 'test find game #name by name'() {
        when:
        def game = service.queryGame(name)

        then:
        game.minPlayers == minPlayers
        game.maxPlayers == maxPlayers
        game.averageDuration == aveDuration

        where:
        name           | minPlayers | maxPlayers | aveDuration
        'SET'          | 2          | 20         | 30
        'Lost Cities'  | 2          | 2          | 30
        'Incan Gold'   | 3          | 8          | 20
        "Witch's Brew" | 3          | 5          | 45
        "Gloomhaven"   | 1          | 4          | 120
    }
    
    def 'test find games with average duration'() {
        when:
        def games = service.queryGamesWithAverageDuration(120)

        then:
        areEqualSets games*.name, ["Gloomhaven", "Power Grid"]
    }

    def 'test find games played between March 21st and March 25th inclusive'() {
        given:
        def startDate = new Date(117, 2, 21)
        def finishDate = new Date(117, 2, 25)

        when:
        def matches = service.queryMatchesPlayedBetweenDates(startDate, finishDate)

        then:
        areEqualSets matches*.game*.name,
                ['Coup: Rebellion G54', 'Power Grid', 'Lost Cities']
    }

    def 'test count how many high scores (from 90 to 100) exist'() {
        expect:
        service.queryHowManyScoresWithinRange(90..100) == 13
    }

    def 'test games expected to last 30 minutes or shorter'() {
        when:
        def games = service.queryGamesExpectedShorterThan(40)

        then:
        areEqualSets games*.name,
                ["Lost Cities", "Dominion", "Metro", "Odin's Ravens", "SET",
                 "Coup: Rebellion G54", "Incan Gold"]
    }

    def 'test games rated 6.0 or better'() {
        when:
        def games = service.queryGamesRatedMoreThan(6.0)

        then:
        areEqualSets games*.name,
                ["Gloomhaven", "Star Wars: Rebellion", "Power Grid", "La Granja",
                 "Dominion", "Runebound", "The Princes of Florence"]
    }

    @Unroll
    def 'test how many games are rated #rating or better'() {
        expect:
        service.queryHowManyGamesRatedAtLeast(rating) == numGames

        where:
        rating | numGames
        2.0    | 25
        4.0    | 24
        6.0    | 8
        8.0    | 2
    }

    @Unroll
    def 'test what players have last name #lastName'() {
        when:
        def players = service.queryPlayersWithLastName(lastName)

        then:
        areEqualSets players*.name, playerNames

        where:
        lastName | playerNames
        'Klein'  | ['Dave Klein', 'Zachary Klein']
        'King'   | ['Paul King']
    }

    @Unroll
    def 'test what mechanics contain text #text'() {
        when:
        def mechanics = service.queryMechanicsContaining(text)

        then:
        areEqualSets mechanics*.name, expectedMechanics

        where:
        text        | expectedMechanics
        'movement'  | ['Area Movement', 'Grid Movement', 'Action/Movement Programming']
        'collect'   | ['Set Collection']
        'placement' | ['Worker Placement', 'Tile Placement']
    }

    def 'test what game names contain the letters J or V (case-insensitive)'() {
        when:
        def games = service.queryGamesMatching(/(?i).*[jv].*/)

        then:
        areEqualSets games*.name,
                ["Gloomhaven", "Vikings", "Odin's Ravens", "Havana", "La Granja"]
    }

    def 'test how many matches are in progress'() {
        expect:
        service.queryHowManyMatchesInProgress() == 9
    }

    def 'test how many matches are completed'() {
        expect:
        service.queryHowManyMatchesCompleted() == 22
    }

    @Unroll
    def 'test how many games support #playerCount players'() {
        expect:
        service.queryHowManyGamesSupportPlayerCount(playerCount) == numGames

        where:
        playerCount | numGames
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

    def 'test what games support exactly two players'() {
        when:
        def games = service.queryGamesSupportExactPlayerCount(2)

        then:
        areEqualSets games*.name, ["Lost Cities", "Odin's Ravens"]
    }

    def 'test find dates of matches played for specified games'() {
        given:
        def games = service.queryGamesForNames(['Dominion', 'SET', 'Space Hulk'])

        when: 'get all matches for the games of interest'
        def matches = service.queryMatchesForGames(games)

        and: 'get the dates on which the match started'
        def startDates = matches*.started.collect { Date dt ->
            LocalDate localDate = dt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
            localDate.getDayOfMonth()
        }

        then:
        startDates.sort() == [4, 7, 11, 12, 16, 30]
    }

    def 'test what games are considered family or party'() {
        when:
        def games = service.queryGamesConsideredFamilyOrParty()

        then:
        areEqualSets games*.name,
                ["Small World", "Fresco", "Flash Point: Fire Rescue", "Lost Cities",
                 "Incan Gold", "Witch's Brew", "Coup: Rebellion G54", "Havana",
                 "Odin's Ravens", "SET", "Metro"]
    }

    def 'test what games are not considered strategy'() {
        when:
        def games = service.queryGamesNotConsideredStrategy()

        then:
        areEqualSets games*.name,
                ["Star Wars: Rebellion", "Flash Point: Fire Rescue", "Lost Cities",
                 "Space Hulk", "Runebound", "Last Night on Earth: The Zombie Game",
                 "Thunder Alley", "Incan Gold", "SET"]
    }

    def 'test what games are not owned by anybody'() {
        when:
        def unownedGames = service.queryGamesOtherThan(ownedGames)

        then:
        areEqualSets unownedGames*.name, ["Metro", "Tournay"]
    }

    def 'test what hand management games are under 45 minutes on average'() {
        given:
        def mechanic = Mechanic.findByName('Hand Management')

        when:
        def games = service.queryGamesWithMechanicNoLongerThanDuration(mechanic, 45)

        then:
        areEqualSets games*.name, ["Dominion", "Lost Cities", "Odin's Ravens"]
    }

    def 'test what economic games are 120 minutes on average'() {
        given:
        def category = Category.findByName('Economic')

        when:
        def games = service.queryGamesInCategoryWithAverageDuration(category, 120)

        then:
        areEqualSets games*.name, ["Power Grid", "Gloomhaven"]
    }
}
