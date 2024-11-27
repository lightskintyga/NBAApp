package com.example.nbaapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.nbaapp.data.model.Player
import com.example.nbaapp.utils.translatePosition

@Composable
fun PlayerItem(player: Player) {
    Card(
        modifier = Modifier.padding(8.dp),
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "${player.first_name} ${player.last_name}", style = MaterialTheme.typography.titleMedium)
            Text(text = "Позиция: ${translatePosition(player.position.ifEmpty { "N/A" })}")
        }
    }
}