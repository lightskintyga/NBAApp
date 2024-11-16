package com.example.nbaapp.ui.navigation

import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.nbaapp.R

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        NavigationItem("teams", "Команды", R.drawable.ball_icon),
        NavigationItem("settings", "Настройки", R.drawable.settings_logo)
    )

    val currentDestination = navController.currentBackStackEntryAsState().value?.destination

    BottomNavigation(
        backgroundColor = Color(0xFFCC5500)
    ) {
        items.forEach { item ->
            val isSelected = currentDestination?.route == item.route
            val iconSize = if (isSelected) 28.dp else 24.dp
            val iconTint = if (isSelected) Color.White else Color.White.copy(alpha = 0.6f)

            BottomNavigationItem(
                icon = {
                    if (item.iconResId != null) {
                        Icon(
                            painter = painterResource(id = item.iconResId),
                            contentDescription = item.label,
                            tint = iconTint,
                            modifier = Modifier.size(iconSize)
                        )
                    } else if (item.iconVector != null) {
                        Icon(
                            imageVector = item.iconVector,
                            contentDescription = item.label,
                            tint = iconTint,
                            modifier = Modifier.size(iconSize)
                        )
                    }
                },
                label = {
                    Text(
                        text = item.label,
                        color = if (isSelected) Color.White else Color.White.copy(alpha = 0.6f),
                        fontSize = if (isSelected) 14.sp else 12.sp
                    )
                },
                selected = isSelected,
                onClick = { navController.navigate(item.route) {
                    popUpTo("teams") {
                        inclusive = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }}
            )
        }
    }
}

data class NavigationItem(
    val route: String,
    val label: String,
    val iconResId: Int? = null,
    val iconVector: ImageVector? = null
)