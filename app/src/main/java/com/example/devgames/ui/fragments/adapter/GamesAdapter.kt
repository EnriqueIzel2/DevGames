package com.example.devgames.ui.fragments.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.devgames.data.model.Game
import com.example.devgames.databinding.GameItemBinding

class GamesAdapter(private val context: Context) :
  RecyclerView.Adapter<GamesAdapter.ViewHolder>() {

  private var gamesList = mutableListOf<Game>()

  inner class ViewHolder(private val binding: GameItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(game: Game) {
      val title = binding.gameTitle
      val backgroundImage = binding.gameBackground
      val rating = binding.gameRating

      title.text = game.name
      Glide.with(context).load(game.backgroundImage).into(backgroundImage)
      rating.text = game.rating.toString()
    }
  }

  fun setGamesList(games: List<Game>) {
    this.gamesList = games.toMutableList()
    notifyDataSetChanged()
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val binding = GameItemBinding.inflate(
      LayoutInflater.from(context),
      parent,
      false
    )

    return ViewHolder(binding)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val game = gamesList[position]

    holder.bind(game)
  }

  override fun getItemCount(): Int = gamesList.size
}