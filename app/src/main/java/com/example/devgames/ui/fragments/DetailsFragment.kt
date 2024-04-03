package com.example.devgames.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.denzcoskun.imageslider.models.SlideModel
import com.example.devgames.data.repository.GameRepository
import com.example.devgames.databinding.FragmentDetailsBinding
import com.example.devgames.service.RetrofitInitializer
import com.example.devgames.viewmodel.GamesViewModel
import com.example.devgames.viewmodel.GamesViewModelFactory

class DetailsFragment : Fragment() {

  private lateinit var binding: FragmentDetailsBinding
  private val args: DetailsFragmentArgs by navArgs()
  private val gameImageSlider by lazy { binding.detailsImageSliderBanner }
  private val gameTitle by lazy { binding.detailsGameTitle }
  private val gameWebsite by lazy { binding.detailsGameWebsite }
  private val gameDescription by lazy { binding.detailsGameDescription }
  private var imageList = mutableListOf(SlideModel(""), SlideModel(""))

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

    gamesViewModel.gameDetails.observe(requireActivity(), Observer { gameDetail ->
      gameTitle.text = gameDetail.name

      imageList[0] = SlideModel(gameDetail.backgroundImage)
      imageList[1] = SlideModel(gameDetail.backgroundImageAdditional)
      gameImageSlider.setImageList(imageList)
    })

    gamesViewModel.errorMessage.observe(this, Observer {
      Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
    })
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