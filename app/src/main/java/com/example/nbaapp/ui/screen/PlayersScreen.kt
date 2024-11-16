package com.example.nbaapp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.nbaapp.ui.component.PlayerItem
import com.example.nbaapp.ui.viewmodel.PlayersViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayersScreen(viewModel: PlayersViewModel, teamId: Int, onBackPressed: () -> Unit) {
    val players by viewModel.players.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val error by viewModel.error.collectAsState()
    val teams by viewModel.teams.collectAsState()

    LaunchedEffect(teamId) {
        viewModel.loadPlayersForTeam(teamId, apiKey = "d8acf9e3-ec8c-4042-bf66-59dc98f426bf")
    }

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = {
                val teamName = teams.find { it.id == teamId }?.full_name ?: "неизвестной команды"
                Text("Состав $teamName", style = MaterialTheme.typography.headlineSmall)
            },
            navigationIcon = {
                IconButton(onClick = onBackPressed) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        )

        if (loading) {
            CircularProgressIndicator()
        } else if (error != null) {
            Text(text = "Error fetching players: $error", color = MaterialTheme.colorScheme.error)
        } else if (players.isNotEmpty()) {
            LazyColumn {
                items(players) { player ->
                    PlayerItem(player)
                }
            }
        } else {
            Text(text = "Нет доступных игроков", color = MaterialTheme.colorScheme.onBackground)
        }
    }
}