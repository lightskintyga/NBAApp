package com.example.nbaapp.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.nbaapp.model.Team
import com.example.nbaapp.viewmodel.TeamsViewModel

@Composable
fun TeamsListScreen(viewModel: TeamsViewModel, onTeamClick: (Team) -> Unit) {
    val teams = viewModel.teamList.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(teams.value) { team ->
            TeamItem(team, onTeamClick)
        }
    }
}

@Composable
fun TeamItem(team: Team, onClick: (Team) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(team) }
            .padding(vertical = 8.dp)
    ) {
        Text(text = team.name, style = MaterialTheme.typography.h6)
        Text(text = "${team.city}, ${team.conference} Conference", style = MaterialTheme.typography.body2)
    }
}