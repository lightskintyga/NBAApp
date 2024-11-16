package com.example.nbaapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nbaapp.domain.model.Team
import com.example.nbaapp.domain.usecase.FetchTeamsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

    class TeamsViewModel(private val fetchTeamsUseCase: FetchTeamsUseCase) : ViewModel() {
    private val _teams = MutableStateFlow<List<Team>>(emptyList())
    val teams: StateFlow<List<Team>> = _teams

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun loadTeams(apiKey: String) {
        _loading.value = true
        viewModelScope.launch {
            try {
                fetchTeamsUseCase(apiKey).collect { fetchedTeams ->
                    _teams.value = fetchedTeams
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