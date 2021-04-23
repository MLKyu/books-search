package com.alansoft.kapaycote.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alansoft.kapaycote.R
import com.alansoft.kapaycote.ui.search.SearchFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SearchFragment.newInstance())
                .commitNow()
        }
    }
}