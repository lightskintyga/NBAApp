package com.example.nbaapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nbaapp.domain.model.Player
import com.example.nbaapp.domain.model.Team
import com.example.nbaapp.domain.usecase.FetchPlayersByTeamUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PlayersViewModel(private val fetchPlayersByTeamUseCase: FetchPlayersByTeamUseCase) : ViewModel() {
    private val _players = MutableStateFlow<List<Player>>(emptyList())
    val players: StateFlow<List<Player>> = _players

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _teams = MutableStateFlow<List<Team>>(emptyList())
    val teams: StateFlow<List<Team>> = _teams

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