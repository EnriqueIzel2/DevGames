package com.example.devgames.ui.fragments.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.devgames.data.model.Game
import com.example.devgames.databinding.GameItemBinding

class GamesAdapter(private val gamesList: List<Game>, private val onItemClicked: (Game) -> Unit) :
  RecyclerView.Adapter<GamesAdapter.ViewHolder>() {

  inner class ViewHolder(private val binding: GameItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private lateinit var game: Game

    init {
      binding.root.setOnClickListener {
        if (::game.isInitialized) {
          onItemClicked(game)
        }
      }
    }

    fun bind(game: Game) {
      this.game = game
      val title = binding.gameTitle
      val backgroundImage = binding.gameBackground
      val rating = binding.gameRating

      title.text = game.name
      Glide.with(binding.root.context).load(game.backgroundImage).into(backgroundImage)
      rating.text = game.rating.toString()
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val binding = GameItemBinding.inflate(
      LayoutInflater.from(parent.context),
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