package com.example.nbaapp.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.nbaapp.domain.model.Player
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