package com.example.devgames.ui.fragments

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.devgames.R
import com.example.devgames.data.repository.GameRepository
import com.example.devgames.viewmodel.GamesViewModel
import com.example.devgames.viewmodel.GamesViewModelFactory
import com.example.devgames.databinding.FragmentHomeBinding
import com.example.devgames.service.RetrofitInitializer
import com.example.devgames.ui.fragments.adapter.GamesAdapter

class HomeFragment : Fragment() {

  private lateinit var binding: FragmentHomeBinding
  private val toolbar by lazy { binding.mainToolbar }
  private val buttonSearchGame by lazy { binding.buttonSearchGame }
  private val recyclerViewGames by lazy { binding.homeRecyclerViewGames }
  private val recyclerViewCategories by lazy { binding.homeRecyclerViewCategories }
  private val mGamesAdapter by lazy { GamesAdapter(requireContext()) }

  private lateinit var gamesViewModel: GamesViewModel
  private val apiInitializer = RetrofitInitializer().createApiService()

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentHomeBinding.inflate(inflater, container, false)

    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    setupToolbar()
    setupViewModel()

    recyclerViewGames.adapter = mGamesAdapter
  }

  override fun onStart() {
    super.onStart()

    gamesViewModel.gamesList.observe(requireActivity(), Observer {
      mGamesAdapter.setGamesList(it)
    })

    gamesViewModel.errorMessage.observe(this, Observer {
      Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
    })
  }

  override fun onResume() {
    super.onResume()

    gamesViewModel.getGames()
  }

  private fun setupViewModel() {
    gamesViewModel =
      ViewModelProvider(requireActivity(), GamesViewModelFactory(GameRepository(apiInitializer))).get(
        GamesViewModel::class.java
      )
  }

  private fun setupToolbar() {
    (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)

    val menuHost: MenuHost = requireActivity()

    val titleToolbar = "DevGames"
    val spannableString = SpannableString(titleToolbar)
    val devColor = ForegroundColorSpan(Color.WHITE)
    spannableString.setSpan(devColor, 0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    val gamesColor = ForegroundColorSpan(resources.getColor(R.color.my_red))
    spannableString.setSpan(gamesColor, 3, titleToolbar.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

    toolbar.title = spannableString

    menuHost.addMenuProvider(object : MenuProvider {
      override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.main_menu, menu)
      }

      override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
          R.id.action_favorites -> {
            Log.i("Menu", "onMenuItemSelected: cliquei")
            true
          }

          else -> false
        }
      }
    }, viewLifecycleOwner, Lifecycle.State.RESUMED)
  }
}