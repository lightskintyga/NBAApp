package com.example.nbaapp.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.nbaapp.data.model.Player
import com.example.nbaapp.data.model.Team
import com.example.nbaapp.repository.BasketballRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BasketballViewModel(private val repository: BasketballRepository) : ViewModel() {
    private val _teams = MutableStateFlow<List<Team>>(emptyList())
    val teams: StateFlow<List<Team>> = _teams

    private val _players = MutableStateFlow<List<Player>>(emptyList())
    val players: StateFlow<List<Player>> = _players

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val playersCache = mutableMapOf<Int, List<Player>>()

    fun loadTeams(apiKey: String) {
        _loading.value = true
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val fetchedTeams = repository.fetchTeams(apiKey)
                _teams.value = fetchedTeams
                _error.value = null
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _loading.value = false
            }
        }
    }

    fun loadPlayersForTeam(teamId: Int, apiKey: String) {
        playersCache[teamId]?.let {
            _players.value = it
            return
        }

        _players.value = emptyList()
        _loading.value = true
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val fetchedPlayers = repository.fetchPlayersByTeam(teamId, apiKey)
                playersCache[teamId] = fetchedPlayers
                _players.value = fetchedPlayers
                _error.value = null
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _loading.value = false
            }
        }
    }
}