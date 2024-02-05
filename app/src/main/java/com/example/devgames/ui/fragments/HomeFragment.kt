package com.example.devgames.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import com.example.devgames.R
import com.example.devgames.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

  private lateinit var binding: FragmentHomeBinding
  private val toolbar by lazy { binding.mainToolbar }
  private val buttonSearchGame by lazy { binding.buttonSearchGame }

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
  }

  private fun setupToolbar() {
    (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)

    val menuHost: MenuHost = requireActivity()

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