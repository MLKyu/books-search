package com.alansoft.kapaycote.utils

import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.alansoft.kapaycote.ui.search.SearchViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

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

fun ImageView.loadWithThumbnail(uri: String?, sizeMultiplier: Float = 0.25f) {
    Glide.with(context)
        .load(uri)
        .thumbnail(sizeMultiplier)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}

fun Fragment.toast(@StringRes message: Int) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}