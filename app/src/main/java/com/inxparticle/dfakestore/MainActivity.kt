package com.inxparticle.dfakestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.inxparticle.dfakestore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()

    val navController:NavController by lazy {
        findNavController(R.id.navHostFragment)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        // Find reference to bottom navigation view
        val navView: BottomNavigationView = binding.bottomNavView

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.dashboardScreen,  R.id.cartScreen , R.id.profileScreen
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navView.setOnItemSelectedListener { menuItem ->
            // Check if the selected item is the current destination
            if (menuItem.itemId != navView.selectedItemId) {
                navController.popBackStack(menuItem.itemId, false )
                navController.navigate(menuItem.itemId)
            }
            true
        }


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
                    R.id.cartScreen -> {
                        binding.bottomNavView.visibility = View.VISIBLE
                        binding.myToolbar.visibility = View.GONE
                    }
                    R.id.dashboardScreen -> {
                        binding.myToolbar.visibility = View.VISIBLE
                        binding.bottomNavView.visibility = View.VISIBLE
                    }
                    R.id.loginScreenFragment ->{
                        binding.bottomNavView.visibility = View.GONE
                        binding.myToolbar.visibility = View.GONE
                    }
                    R.id.profileScreen ->{
                        binding.bottomNavView.visibility = View.VISIBLE
                        binding.myToolbar.visibility = View.GONE
                    }
                }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var foundID = false
        navController.addOnDestinationChangedListener{
                _,destination,_->
            when (destination.id) {
                R.id.dashboardScreen ->{
                    menu?.clear()
                    menuInflater.inflate(R.menu.home_menu, menu)
                    foundID = true
                }                }
            }
        if(foundID){
            return true
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}