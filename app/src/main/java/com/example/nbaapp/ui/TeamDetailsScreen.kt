package com.example.nbaapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.nbaapp.data.model.Team
import com.example.nbaapp.ui.components.TeamItem
import com.example.nbaapp.viewmodel.BasketballViewModel
import com.example.nbaapp.utils.translateConference
import com.example.nbaapp.utils.translateDivision

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamDetailsScreen(team: Team, onBackPressed: () -> Unit, onPlayersClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopAppBar(
            title = { Text(team.full_name) },
            navigationIcon = {
                IconButton(onClick = onBackPressed) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Назад")
                }
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Город: ${team.city}", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(start = 16.dp))
        Text(text = "Конференция: ${translateConference(team.conference)}", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(start = 16.dp))
        Text(text = "Дивизион: ${translateDivision(team.division)}", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(start = 16.dp))

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onPlayersClick,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Посмотреть состав")
        }
    }
}