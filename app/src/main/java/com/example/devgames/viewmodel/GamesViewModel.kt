package com.example.devgames.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.devgames.data.model.Game
import com.example.devgames.data.model.GameDetails
import com.example.devgames.data.model.GameResult
import com.example.devgames.data.repository.GameRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

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
        Log.e("Details Debug", "onResponse: ${t.message}")
        errorMessage.postValue(t.message)
      }
    })
  }

  val gameDetails = MutableLiveData<GameDetails>()
  fun getGame(id: Int) {
    val request = repository.getGame(id)
    request.enqueue(object : Callback<GameDetails> {
      override fun onResponse(call: Call<GameDetails>, response: Response<GameDetails>) {
        Log.i("Details Debug", "onResponse: body: ${response.body()}")
        gameDetails.postValue(response.body())
      }

      override fun onFailure(call: Call<GameDetails>, t: Throwable) {
        Log.e("Details Debug", "onResponse: ${t.message}")
        errorMessage.postValue(t.message)
      }
    })
  }
}