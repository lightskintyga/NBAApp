package com.example.nbaapp.ui

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
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.nbaapp.NbaappTheme
import com.example.nbaapp.api.BallDontLieApi
import com.example.nbaapp.repository.BasketballRepository
import com.example.nbaapp.ui.components.BottomNavigationBar
import com.example.nbaapp.viewmodel.BasketballViewModel
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: BasketballViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val client = OkHttpClient.Builder()
            .addInterceptor(ChuckerInterceptor(this))
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.balldontlie.io/v1/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(BallDontLieApi::class.java)
        val repository = BasketballRepository(api)
        viewModel = BasketballViewModel(repository)

        viewModel.loadTeams("d8acf9e3-ec8c-4042-bf66-59dc98f426bf")

        setContent {
            NbaappTheme {
                NavHostController(viewModel)
            }
        }
    }

    @Composable
    fun NavHostController(viewModel: BasketballViewModel) {
        val navController = rememberNavController()

        Scaffold(
            bottomBar = { BottomNavigationBar(navController) }
        ) { innerPadding ->
            NavHost(navController, startDestination = "teams", Modifier.padding(innerPadding)) {
                composable("teams") {
                    TeamsScreen(viewModel) { teamId ->
                        navController.navigate("teamDetails/$teamId")
                    }
                }
                composable("settings") { SettingsScreen() }
                composable("teamDetails/{teamId}") { backStackEntry ->
                    val teamId = backStackEntry.arguments?.getString("teamId")?.toIntOrNull()
                    val team = teamId?.let { id -> viewModel.teams.value.find { it.id == id } }
                    if (team != null) {
                        TeamDetailsScreen(
                            team,
                            onBackPressed = { navController.popBackStack() },
                            onPlayersClick = {
                                viewModel.loadPlayersForTeam(teamId, "d8acf9e3-ec8c-4042-bf66-59dc98f426bf")
                                navController.navigate("players/$teamId")
                            }
                        )
                    }
                }
                composable("players/{teamId}") { backStackEntry ->
                    val teamId = backStackEntry.arguments?.getString("teamId")?.toIntOrNull()
                    if (teamId != null) {
                        PlayersScreen(viewModel, teamId) { navController.popBackStack() }
                    }
                }
            }
        }
    }
}