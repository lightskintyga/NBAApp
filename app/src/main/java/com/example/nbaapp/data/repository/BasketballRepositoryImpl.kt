package com.example.nbaapp.data.repository

import com.example.nbaapp.data.source.RemoteDataSource
import com.example.nbaapp.domain.model.Player
import com.example.nbaapp.domain.model.Team
import com.example.nbaapp.domain.repository.BasketballRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BasketballRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    BasketballRepository {

    override suspend fun fetchTeams(apiKey: String): Flow<List<Team>> {
        return remoteDataSource.getTeams(apiKey)
    }

    override suspend fun fetchPlayersByTeam(teamId: Int, apiKey: String): Flow<List<Player>> {
        return remoteDataSource.getPlayersByTeam(teamId, apiKey)
    }
}