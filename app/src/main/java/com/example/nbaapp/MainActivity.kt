package com.example.nbaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nbaapp.api.RetrofitClient
import com.example.nbaapp.data.repository.BasketballRepositoryImpl
import com.example.nbaapp.data.source.RemoteDataSource
import com.example.nbaapp.domain.repository.BasketballRepository
import com.example.nbaapp.domain.usecase.FetchPlayersByTeamUseCase
import com.example.nbaapp.domain.usecase.FetchTeamsUseCase
import com.example.nbaapp.ui.navigation.BottomNavigationBar
import com.example.nbaapp.ui.screen.PlayersScreen
import com.example.nbaapp.ui.screen.SettingsScreen
import com.example.nbaapp.ui.screen.TeamDetailsScreen
import com.example.nbaapp.ui.screen.TeamsScreen
import com.example.nbaapp.ui.theme.NbaTheme
import com.example.nbaapp.ui.viewmodel.PlayersViewModel
import com.example.nbaapp.ui.viewmodel.TeamDetailsViewModel
import com.example.nbaapp.ui.viewmodel.TeamsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: TeamsViewModel
    private lateinit var teamDetailsViewModel: TeamDetailsViewModel
    private lateinit var playersViewModel: PlayersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val apiKey = "d8acf9e3-ec8c-4042-bf66-59dc98f426bf"
        val api = RetrofitClient.create(applicationContext, apiKey)
        val remoteDataSource = RemoteDataSource(api)
        val repository: BasketballRepository = BasketballRepositoryImpl(remoteDataSource)
        val fetchTeamsUseCase = FetchTeamsUseCase(repository)
        val fetchPlayersByTeamUseCase = FetchPlayersByTeamUseCase(repository)

        viewModel = TeamsViewModel(fetchTeamsUseCase)
        teamDetailsViewModel = TeamDetailsViewModel(fetchTeamsUseCase, fetchPlayersByTeamUseCase)
        playersViewModel = PlayersViewModel(fetchPlayersByTeamUseCase)

        CoroutineScope(Dispatchers.IO).launch {
            viewModel.loadTeams(apiKey)
        }

        setContent {
            NbaTheme {
                NavHostController(viewModel, teamDetailsViewModel, playersViewModel)
            }
        }
    }

    @Composable
    fun NavHostController(viewModel: TeamsViewModel, teamDetailsViewModel: TeamDetailsViewModel, playersViewModel: PlayersViewModel) {
        val navController = rememberNavController()

        Scaffold(
            bottomBar = { BottomNavigationBar(navController) }
        ) { innerPadding ->
            NavHost(navController, startDestination = "teams", Modifier.padding(innerPadding)) {
                composable("teams") {
                    TeamsScreen(viewModel) { teamId ->
                        teamDetailsViewModel.loadTeamDetails(teamId, "d8acf9e3-ec8c-4042-bf66-59dc98f426bf")
                        navController.navigate("teamDetails/$teamId")
                    }
                }
                composable("settings") { SettingsScreen() }
                composable("teamDetails/{teamId}") { backStackEntry ->
                    val teamId = backStackEntry.arguments?.getString("teamId")?.toIntOrNull()
                    if (teamId != null) {
                        TeamDetailsScreen(
                            viewModel = teamDetailsViewModel,
                            teamId = teamId,
                            onBackPressed = { navController.popBackStack() },
                            onPlayersClick = {
                                teamDetailsViewModel.loadPlayersForTeam(teamId, "d8acf9e3-ec8c-4042-bf66-59dc98f426bf")
                                navController.navigate("players/$teamId")
                            }
                        )
                    }
                }
                composable("players/{teamId}") { backStackEntry ->
                    val teamId = backStackEntry.arguments?.getString("teamId")?.toIntOrNull()
                    if (teamId != null) {
                        playersViewModel.loadPlayersForTeam(teamId, "d8acf9e3-ec8c-4042-bf66-59dc98f426bf")
                        PlayersScreen(playersViewModel, teamId) { navController.popBackStack() }
                    }
                }
            }
        }
    }
}