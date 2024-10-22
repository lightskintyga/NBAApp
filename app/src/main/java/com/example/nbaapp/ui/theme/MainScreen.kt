package com.example.nbaapp.ui.theme

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.nbaapp.R
import com.example.nbaapp.viewmodel.TeamsViewModel

@Composable
fun MainScreen(viewModel: TeamsViewModel) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        NavHost(navController = navController, startDestination = "teams", modifier = Modifier.padding(innerPadding)) {
            composable("teams") {
                TeamsListScreen(viewModel = viewModel, onTeamClick = { team ->
                    navController.navigate("teamDetail/${team.id}")
                })
            }
            composable("teamDetail/{teamId}") { backStackEntry ->
                val teamId = backStackEntry.arguments?.getString("teamId")?.toIntOrNull()
                val team = viewModel.getTeamById(teamId ?: 0)
                team?.let {
                    TeamDetailScreen(team = it, onBackPress = { navController.popBackStack() })
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem("Команды", R.drawable.list_logo, "teams"),
        BottomNavItem("Настройки", R.drawable.settings_logo, "settings")
    )

    BottomNavigation(elevation = 8.dp) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                label = { Text(text = item.title) },
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route)
                    }
                }
            )
        }
    }
}

data class BottomNavItem(val title: String, val icon: Int, val route: String)