package com.example.nbaapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.nbaapp.data.model.Team

@Composable
fun TeamItem(team: Team, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = team.full_name.ifEmpty { "Неизвестная команда" }, style = MaterialTheme.typography.titleMedium)
            Text(text = team.city.ifEmpty { "N/A" }, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.secondary)
            Text(text = team.abbreviation.ifEmpty { "N/A" }, style = MaterialTheme.typography.bodySmall)
        }
    }
}