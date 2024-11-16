package com.example.nbaapp.domain.repository

import com.example.nbaapp.domain.model.Player
import com.example.nbaapp.domain.model.Team
import kotlinx.coroutines.flow.Flow

interface BasketballRepository {
    suspend fun fetchTeams(apiKey: String): Flow<List<Team>>
    suspend fun fetchPlayersByTeam(teamId: Int, apiKey: String): Flow<List<Player>>
}