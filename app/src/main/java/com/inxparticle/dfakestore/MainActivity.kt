package com.inxparticle.dfakestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.inxparticle.dfakestore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.viewModel = viewModel

        val navController = this.findNavController(R.id.navHostFragment)

        // Find reference to bottom navigation view
        val navView: BottomNavigationView = findViewById(R.id.bottom_nav_view)
// Hook your navigation controller to bottom navigation view
        navView.setupWithNavController(navController)

    }
}