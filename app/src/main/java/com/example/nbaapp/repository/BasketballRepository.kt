package com.example.nbaapp.repository

import com.example.nbaapp.api.BallDontLieApi
import com.example.nbaapp.data.model.Player
import com.example.nbaapp.data.model.Team

class BasketballRepository(private val api: BallDontLieApi) {
    suspend fun fetchTeams(apiKey: String): List<Team> {
        return api.getTeams(apiKey).data
    }

    suspend fun fetchPlayersByTeam(teamId: Int, apiKey: String): List<Player> {
        return api.getPlayersByTeam(teamId, apiKey).data
    }
}