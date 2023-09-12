package com.inxparticle.dfakestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController

class MainActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = findNavController(R.id.navHostFragment)
        toolbar = findViewById(R.id.toolbar)

        setupToolbar(toolbar, navController)
    }

    private fun setupToolbar(toolbar: Toolbar, navController: NavController) {
        setSupportActionBar(toolbar)

        val topLevelDestinations = setOf(R.id.splashScreenFragment, R.id.dashboardScreenFragment)
        val config = AppBarConfiguration.Builder(topLevelDestinations).build()
        toolbar.setupWithNavController(navController, config)
    }
}