package com.alansoft.kapaycote.ui

import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.alansoft.kapaycote.R
import com.alansoft.kapaycote.databinding.MainActivityBinding
import dagger.hilt.android.AndroidEntryPoint
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

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

        getHashKey()
    }

    private fun getHashKey() {
        var packageInfo: PackageInfo? = null
        try {
            packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        for (signature in packageInfo?.signatures ?: return) {
            try {
                val md: MessageDigest = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                Log.d("KeyHash %s", Base64.encodeToString(md.digest(), Base64.DEFAULT))
            } catch (e: NoSuchAlgorithmException) {
                Log.e("aaaaaaa", "KeyHash Unable to get MessageDigest. signature=$signature$e")
            }
        }
    }
}