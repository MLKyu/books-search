package com.alansoft.kapaycote.utils

import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.alansoft.kapaycote.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

/**
 * Created by LEE MIN KYU on 2021/04/25
 * Copyright © 2021 Dreamus Company. All rights reserved.
 */
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

fun MenuItem.setSelector(select: Boolean) {
    if (select) {
        setIcon(R.drawable.ic_like_active)
    } else {
        setIcon(R.drawable.ic_like_inactive)
    }
}

fun <T> Fragment.getNavigationResult(@IdRes destinationId: Int, key: String = "result") =
    findNavController().getBackStackEntry(destinationId).savedStateHandle.getLiveData<T>(key)

fun <T> Fragment.setNavigationResult(result: T, key: String = "result") {
    findNavController().previousBackStackEntry?.savedStateHandle?.set(key, result)
}