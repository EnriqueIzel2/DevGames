package com.example.devgames.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.devgames.data.repository.GameRepository
import com.example.devgames.databinding.FragmentDetailsBinding
import com.example.devgames.service.RetrofitInitializer
import com.example.devgames.viewmodel.GamesViewModel
import com.example.devgames.viewmodel.GamesViewModelFactory

class DetailsFragment : Fragment() {

  private lateinit var binding: FragmentDetailsBinding
  private val args: DetailsFragmentArgs by navArgs()
  private val gameMainBanner by lazy { binding.detailsMainBanner }
  private val gameTitle by lazy { binding.detailsGameTitle }
  private val gameDescription by lazy { binding.detailsGameDescription }

  private lateinit var gamesViewModel: GamesViewModel
  private val apiInitializer = RetrofitInitializer().createApiService()

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentDetailsBinding.inflate(inflater, container, false)

    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    setupViewModel()
  }

  override fun onStart() {
    super.onStart()

    gameTitle.text = gamesViewModel.gameDetails.value?.name

    //    Glide.with(binding.root.context).load(game.backgroundImage).into(gameMainBanner)
//    gameTitle.text = game.name
//    Log.i("Details debug", "onViewCreated: ${game.description}")
  }

  override fun onResume() {
    super.onResume()

    gamesViewModel.getGame(args.gameID)
  }

  private fun setupViewModel() {
    gamesViewModel =
      ViewModelProvider(
        requireActivity(),
        GamesViewModelFactory(GameRepository(apiInitializer))
      ).get(GamesViewModel::class.java)
  }
}