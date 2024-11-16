package com.example.nbaapp.domain.usecase

import com.example.nbaapp.domain.model.Team
import com.example.nbaapp.domain.repository.BasketballRepository
import kotlinx.coroutines.flow.Flow

class FetchTeamsUseCase(private val repository: BasketballRepository) {
    suspend operator fun invoke(apiKey: String): Flow<List<Team>> {
        return repository.fetchTeams(apiKey)
    }
}