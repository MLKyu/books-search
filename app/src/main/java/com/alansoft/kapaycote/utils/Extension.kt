package com.alansoft.kapaycote.utils

import androidx.appcompat.widget.SearchView
import com.alansoft.kapaycote.ui.search.SearchViewModel

/**
 * Created by LEE MIN KYU on 2021/04/25
 * Copyright © 2021 Dreamus Company. All rights reserved.
 */
fun SearchView.setOnQueryListener(delegate: SearchViewModel.Delegate) {
    this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            if (!query.isNullOrEmpty()) {
                delegate.querySubmitted(query)
                setQuery("", false)
                clearFocus()
            }
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            val query: CharSequence = this@setOnQueryListener.query
            if (query.length >= 2) {
                delegate.queryChanged(query.toString())
            }
            return false
        }
    })
}