package com.example.devgames.data.model

import com.google.gson.annotations.SerializedName

data class GameDetails(
  val id: Int,
  val name: String,
  val description: String,
  val rating: Double,
  @SerializedName("background_image")
  val backgroundImage: String,
  @SerializedName("background_image_additional")
  val backgroundImageAdditional: String,
//  val platforms: List<Platform>,
//  val stores: List<String>,
)
