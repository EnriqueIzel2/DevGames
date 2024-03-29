package com.example.devgames.data.repository

import com.example.devgames.service.ApiService

class GameRepository(private val apiService: ApiService) {

  fun getGames() = apiService.getGames()

  fun getGame(id: Int) = apiService.getGame(id = id)
}