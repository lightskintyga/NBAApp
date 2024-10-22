package com.example.nbaapp.network

import com.example.nbaapp.model.Team
import retrofit2.http.GET

interface NBAApiService {
    @GET("teams")
    suspend fun getTeams(): List<Team>
}