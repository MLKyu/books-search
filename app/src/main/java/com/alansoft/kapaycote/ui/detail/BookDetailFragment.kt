package com.alansoft.kapaycote.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.alansoft.kapaycote.R
import com.alansoft.kapaycote.data.response.Document
import com.alansoft.kapaycote.databinding.BookDetailFragmentBinding
import com.alansoft.kapaycote.utils.loadWithThumbnail
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by LEE MIN KYU on 2021/04/20
 * Copyright © 2021 Dreamus Company. All rights reserved.
 */

@AndroidEntryPoint
class BookDetailFragment : Fragment() {
    private lateinit var binding: BookDetailFragmentBinding
    private val viewModel: BookDetailViewModel by viewModels()
    private val args: BookDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.book_detail_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView(args.document)
    }

    private fun bindView(document: Document) {
        with(binding) {
            bookImg.loadWithThumbnail(document.thumbnail)
            bookDate.text = document.datetime
            bookDes.text = document.contents
            bookName.text = document.title
            bookPrice.text = document.salePrice.toString()
            bookPublisher.text = document.publisher
            //            binding.bookLike

            progressBar.visibility = View.GONE
        }
    }
}