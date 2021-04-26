package com.alansoft.kapaycote.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.alansoft.kapaycote.R
import com.alansoft.kapaycote.databinding.MainActivityBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by LEE MIN KYU on 2021/04/20
 * Copyright © 2021 Dreamus Company. All rights reserved.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: MainActivityBinding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        val navController: NavController = navHostFragment.navController
        binding.toolbar.setupWithNavController(navController)
        setSupportActionBar(binding.toolbar)
    }
}