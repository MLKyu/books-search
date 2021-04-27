package com.alansoft.kapaycote.utils

import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter

/**
 * Created by LEE MIN KYU on 2021/04/25
 * Copyright © 2021 Dreamus Company. All rights reserved.
 */

@BindingAdapter("loadImg")
fun laodImg(view: ImageView, url: String) {
    view.loadWithThumbnail(url)
}

@BindingAdapter("selected")
fun selected(view: AppCompatImageView, selected: Boolean) {
    view.isSelected = selected
}