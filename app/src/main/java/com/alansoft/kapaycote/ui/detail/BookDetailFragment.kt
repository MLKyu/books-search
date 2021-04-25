package com.alansoft.kapaycote.ui.detail

import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by LEE MIN KYU on 2021/04/20
 * Copyright © 2021 Dreamus Company. All rights reserved.
 */

@AndroidEntryPoint
class BookDetailFragment : Fragment() {
    companion object {
        val instance by lazy { BookDetailFragment() }
    }
}