package com.example.nbaapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.nbaapp.data.model.Team
import com.example.nbaapp.viewmodel.BasketballViewModel
import com.example.nbaapp.ui.components.TeamItem

@Composable
fun TeamsScreen(viewModel: BasketballViewModel, onTeamSelected: (Int) -> Unit) {
    val teams by viewModel.teams.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val error by viewModel.error.collectAsState()

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Команды", style = MaterialTheme.typography.titleLarge)

        if (loading) {
            CircularProgressIndicator()
        } else if (error != null) {
            Text(text = "Error fetching teams: $error", color = MaterialTheme.colorScheme.error)
        } else if (teams.isNotEmpty()) {
            LazyColumn {
                items(teams) { team ->
                    TeamItem(team, onClick = { onTeamSelected(team.id) })
                }
            }
        } else {
            Text(text = "Нет доступных команд", color = MaterialTheme.colorScheme.onBackground)
        }
    }
}