package com.example.nbaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.nbaapp.ui.theme.MainScreen
import com.example.nbaapp.ui.theme.NbaappTheme
import com.example.nbaapp.viewmodel.TeamsViewModel

class MainActivity : ComponentActivity() {
    private val teamsViewModel: TeamsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NbaappTheme {
                MainScreen(viewModel = teamsViewModel)
            }
        }
    }
}