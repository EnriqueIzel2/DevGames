package com.example.devgames.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.devgames.data.model.Game
import com.example.devgames.data.model.GameResult
import com.example.devgames.data.repository.GameRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GamesViewModel(private val repository: GameRepository) : ViewModel() {

  val gamesList = MutableLiveData<List<Game>>()
  val errorMessage = MutableLiveData<String>()

  fun getGames() {
    val request = repository.getGames()
    request.enqueue(object : Callback<GameResult> {
      override fun onResponse(call: Call<GameResult>, response: Response<GameResult>) {
        gamesList.postValue(response.body()?.results)
      }

      override fun onFailure(call: Call<GameResult>, t: Throwable) {
        errorMessage.postValue(t.message)
      }
    })
  }
}