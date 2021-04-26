package com.alansoft.kapaycote.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 * Created by LEE MIN KYU on 2021/04/20
 * Copyright © 2021 Dreamus Company. All rights reserved.
 */
abstract class BaseFragment<VB : ViewDataBinding> : Fragment() {

    protected lateinit var binding: VB
    abstract fun getLayoutId(): Int

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }
}