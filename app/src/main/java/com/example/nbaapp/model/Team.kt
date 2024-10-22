package com.example.nbaapp.model

data class Team(
    val id: Int,
    val name: String,
    val city: String,
    val abbreviation: String,
    val conference: String,
    val gamesPlayed: Int,
    val gamesWin: Int,
    val gamesLose: Int,
    val winsPercentage: Double
)