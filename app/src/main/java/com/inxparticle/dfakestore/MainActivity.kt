package com.inxparticle.dfakestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.inxparticle.dfakestore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()

    private val navController by lazy{
        Navigation.findNavController(this,R.id.navHostFragment)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        binding.viewModel = viewModel

        // Find reference to bottom navigation view
        val navView: BottomNavigationView = binding.bottomNavView
        // Hook your navigation controller to bottom navigation view
        navView.setupWithNavController(navController)

        setupDrawerLayout()
        showHideNavigationDrawerAndBottomNavigationBar()
    }

    private fun showHideNavigationDrawerAndBottomNavigationBar() {
        navController.addOnDestinationChangedListener{
            controller,destination,argument->
                when (destination.id) {
                    R.id.splashScreenFragment -> {
                        binding.bottomNavView.visibility = View.GONE
                        binding.myToolbar.visibility = View.GONE
                    }
                    R.id.cartScreenFragment -> {
                        binding.bottomNavView.visibility = View.GONE
                    }
                    R.id.dashboardScreenFragment -> {
                        binding.bottomNavView.visibility = View.VISIBLE
                    }
                    R.id.loginScreenFragment ->{
                        binding.bottomNavView.visibility = View.GONE
                        binding.myToolbar.visibility = View.GONE
                    }
                }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, binding.drawerLayout)
    }

    private fun setupDrawerLayout() {
        binding.navView.setupWithNavController(navController)
        NavigationUI.setupActionBarWithNavController(this, navController, binding.drawerLayout)
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}