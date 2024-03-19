package com.example.devgames.service

import com.example.devgames.API_KEY
import com.example.devgames.data.model.GameResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
  companion object {
    private const val api_key = API_KEY
  }

  @GET("games")
  fun getGames(@Query("key") api: String = api_key) : Call<GameResult>
}