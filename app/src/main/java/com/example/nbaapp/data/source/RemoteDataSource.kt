package com.example.nbaapp.data.source

import com.example.nbaapp.api.BallDontLieApi
import com.example.nbaapp.domain.model.Player
import com.example.nbaapp.domain.model.Team
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val api: BallDontLieApi) {

    suspend fun getTeams(apiKey: String): Flow<List<Team>> = flow {
        val response = api.getTeams(apiKey)
        emit(response.data)
    }

    suspend fun getPlayersByTeam(teamId: Int, apiKey: String): Flow<List<Player>> = flow {
        val response = api.getPlayersByTeam(teamId, apiKey)
        emit(response.data)
    }
}