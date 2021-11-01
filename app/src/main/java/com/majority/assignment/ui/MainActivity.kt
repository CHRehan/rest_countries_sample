package com.majority.assignment.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.majority.assignment.R
import com.majority.assignment.databinding.ActivityMainBinding
import com.majority.assignment.viewmodels.CountriesViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController // don't forget to initialize
    private lateinit var listener: NavController.OnDestinationChangedListener // don't forget to initialize

    private val countriesViewModel by viewModels<CountriesViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navHostFragment: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)



        listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            if (R.id.CountriesFragment == destination.id) {
                binding.toolbar.inflateMenu(R.menu.menu_main)
                setSearchBar(binding)


            } else {
                binding.toolbar.menu.clear()

            }
        }
        navController.addOnDestinationChangedListener(listener)


    }

    private fun setSearchBar(binding: ActivityMainBinding) {
        val menuItem: MenuItem? = binding.toolbar.menu.findItem(R.id.action_search)
        val searchView: SearchView = menuItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {

                countriesViewModel.setSearchQuery(newText)
                return true
            }
        })
    }


    override fun onDestroy() {
        navController.removeOnDestinationChangedListener(listener)
        super.onDestroy()
    }


}
