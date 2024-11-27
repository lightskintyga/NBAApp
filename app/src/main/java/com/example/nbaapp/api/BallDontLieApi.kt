package com.example.nbaapp.api

import com.example.nbaapp.data.model.PlayersResponse
import com.example.nbaapp.data.model.TeamsResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface BallDontLieApi {
    @GET("teams")
    suspend fun getTeams(@Header("Authorization") apiKey: String): TeamsResponse

    @GET("players")
    suspend fun getPlayersByTeam(@Query("team_ids[]") teamId: Int, @Header("Authorization") apiKey: String): PlayersResponse
}