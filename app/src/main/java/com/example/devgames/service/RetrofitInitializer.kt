package com.example.devgames.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {
  companion object {
    const val base_url = "https://api.rawg.io/api/"
  }

  private fun getRetrofitClient(): Retrofit {
    val builder = Retrofit.Builder()

    builder.baseUrl(base_url).addConverterFactory(GsonConverterFactory.create())

    return builder.build()
  }

  fun createApiService(): ApiService {
    return getRetrofitClient().create(ApiService::class.java)
  }
}