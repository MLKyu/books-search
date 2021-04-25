package com.alansoft.kapaycote.utils

import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.databinding.BindingAdapter
import com.alansoft.kapaycote.ui.search.SearchViewModel
import com.bumptech.glide.Glide

/**
 * Created by LEE MIN KYU on 2021/04/25
 * Copyright © 2021 Dreamus Company. All rights reserved.
 */

@BindingAdapter("loadImg")
fun laodImg(view: ImageView, url: String?) {
    if (url != null) {
        Glide.with(view).load(url).fitCenter()
//            .error(R.drawable.error)
            .into(view)
    }
}

@BindingAdapter("queryListener")
fun setOnQueryListener(searchView: SearchView, delegate: SearchViewModel.Delegate) {
    searchView.setOnQueryListener(delegate)
}