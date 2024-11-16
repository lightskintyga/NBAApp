package com.example.nbaapp.domain.usecase

import com.example.nbaapp.domain.model.Player
import com.example.nbaapp.domain.repository.BasketballRepository
import kotlinx.coroutines.flow.Flow

class FetchPlayersByTeamUseCase(private val repository: BasketballRepository) {
    suspend operator fun invoke(teamId: Int, apiKey: String): Flow<List<Player>> {
        return repository.fetchPlayersByTeam(teamId, apiKey)
    }
}