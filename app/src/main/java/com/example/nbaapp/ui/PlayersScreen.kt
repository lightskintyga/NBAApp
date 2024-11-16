package com.example.nbaapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.nbaapp.data.model.Player
import com.example.nbaapp.ui.components.PlayerItem
import com.example.nbaapp.viewmodel.BasketballViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayersScreen(viewModel: BasketballViewModel, teamId: Int, onBackPressed: () -> Unit) {
    val players by viewModel.players.collectAsState()
    val loading by viewModel.loading.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = {
                Text("Состав ${viewModel.teams.collectAsState().value.find { it.id == teamId }?.full_name ?: "неизвестной команды"}")
            },
            navigationIcon = {
                IconButton(onClick = onBackPressed) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        )

        if (loading) {
            CircularProgressIndicator()
        } else {
            LazyColumn {
                items(players) { player ->
                    PlayerItem(player)
                }
            }
        }
    }
}