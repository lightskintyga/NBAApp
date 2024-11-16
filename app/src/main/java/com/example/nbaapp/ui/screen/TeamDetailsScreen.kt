package com.example.nbaapp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.nbaapp.ui.component.PlayerItem
import com.example.nbaapp.ui.viewmodel.TeamDetailsViewModel
import com.example.nbaapp.utils.translateConference
import com.example.nbaapp.utils.translateDivision

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamDetailsScreen(
    viewModel: TeamDetailsViewModel,
    teamId: Int,
    onBackPressed: () -> Unit,
    onPlayersClick: () -> Unit
) {
    val team by viewModel.team.collectAsState()
    val isLoading by viewModel.loading.collectAsState()
    val navController = rememberNavController()

    if (isLoading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
    } else {
        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBar(
                title = { Text(team?.full_name ?: "Неизвестная команда", style = MaterialTheme.typography.headlineSmall) },
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Назад")
                    }
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            team?.let {
                Text(text = "Город: ${it.city}", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(start = 16.dp))
                Text(text = "Конференция: ${translateConference(it.conference)}", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(start = 16.dp))
                Text(text = "Дивизион: ${translateDivision(it.division)}", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(start = 16.dp))
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onPlayersClick,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Посмотреть состав")
            }
        }
    }
}