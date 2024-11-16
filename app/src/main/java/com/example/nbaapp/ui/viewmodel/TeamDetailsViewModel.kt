package com.example.nbaapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nbaapp.domain.model.Player
import com.example.nbaapp.domain.model.Team
import com.example.nbaapp.domain.usecase.FetchPlayersByTeamUseCase
import com.example.nbaapp.domain.usecase.FetchTeamsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TeamDetailsViewModel(
    private val fetchTeamsUseCase: FetchTeamsUseCase,
    private val fetchPlayersByTeamUseCase: FetchPlayersByTeamUseCase
) : ViewModel() {
    private val _team = MutableStateFlow<Team?>(null)
    val team: StateFlow<Team?> = _team

    private val _players = MutableStateFlow<List<Player>>(emptyList())
    val players: StateFlow<List<Player>> = _players

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun loadTeamDetails(teamId: Int, apiKey: String) {
        _loading.value = true
        viewModelScope.launch {
            try {
                fetchTeamsUseCase(apiKey).collect { teams ->
                    _team.value = teams.find { it.id == teamId }
                }
            } catch (e: Exception) {
                _team.value = null
                _error.value = e.message
            } finally {
                _loading.value = false
            }
        }
    }

    fun loadPlayersForTeam(teamId: Int, apiKey: String) {
        _loading.value = true
        viewModelScope.launch {
            try {
                fetchPlayersByTeamUseCase(teamId, apiKey).collect { fetchedPlayers ->
                    _players.value = fetchedPlayers
                    _error.value = null
                }
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _loading.value = false
            }
        }
    }
}