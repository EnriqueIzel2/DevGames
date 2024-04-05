package com.example.devgames.ui.fragments.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.devgames.databinding.GameTagsItemBinding

class TagsItemAdapter(private val tagsList: List<String>) : RecyclerView.Adapter<TagsItemAdapter.ViewHolder>() {

  inner class ViewHolder(private val binding: GameTagsItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

      fun bind(tagName: String) {
        binding.itemTagName.text = tagName
      }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val binding = GameTagsItemBinding.inflate(
      LayoutInflater.from(parent.context),
      parent,
      false
    )

    return ViewHolder(binding)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val tagName = tagsList[position]

    holder.bind(tagName)
  }

  override fun getItemCount(): Int = tagsList.size
}