package com.example.nbaapp.network

import com.example.nbaapp.model.Team
import kotlinx.coroutines.delay

class FakeNBAApiService : NBAApiService {
    override suspend fun getTeams(): List<Team> {
        delay(1000)

        return listOf(
            Team(id = 1, name = "Los Angeles Lakers", city = "Los Angeles", abbreviation = "LAL", conference = "West", gamesPlayed = 40, gamesWin = 19, gamesLose = 21, winsPercentage = 0.475),
            Team(id = 2, name = "Golden State Warriors", city = "San Francisco", abbreviation = "GSW", conference = "West", gamesPlayed = 41, gamesWin = 25, gamesLose = 16, winsPercentage = 0.610),
            Team(id = 3, name = "Brooklyn Nets", city = "Brooklyn", abbreviation = "BKN", conference = "East", gamesPlayed = 41, gamesWin = 12, gamesLose = 29, winsPercentage = 0.293),
            Team(id = 4, name = "Miami Heat", city = "Miami", abbreviation = "MIA", conference = "East", gamesPlayed = 41, gamesWin = 24, gamesLose = 17, winsPercentage = 0.585)
        )
    }
}