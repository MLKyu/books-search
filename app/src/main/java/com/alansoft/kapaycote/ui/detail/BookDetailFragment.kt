package com.alansoft.kapaycote.ui.detail

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.alansoft.kapaycote.R
import com.alansoft.kapaycote.data.response.Document
import com.alansoft.kapaycote.databinding.BookDetailFragmentBinding
import com.alansoft.kapaycote.ui.base.BaseFragment
import com.alansoft.kapaycote.utils.loadWithThumbnail
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by LEE MIN KYU on 2021/04/20
 * Copyright © 2021 Dreamus Company. All rights reserved.
 */

@AndroidEntryPoint
class BookDetailFragment : BaseFragment<BookDetailFragmentBinding>() {
    private val viewModel: BookDetailViewModel by viewModels()
    private val args: BookDetailFragmentArgs by navArgs()

    override fun getLayoutId(): Int = R.layout.book_detail_fragment
    private var like = false

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.detail_menu_item, menu)
        setSearchView(menu)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
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

    private fun setSearchView(menu: Menu) {
        val likeItem = menu.findItem(R.id.action_like)
        if (like) {
            likeItem.setIcon(R.drawable.ic_like_active)
        } else {
            likeItem.setIcon(R.drawable.ic_like_inactive)
        }
        likeItem.setOnMenuItemClickListener {
            like = !like
            if (like) {
                it.setIcon(R.drawable.ic_like_active)
            } else {
                it.setIcon(R.drawable.ic_like_inactive)
            }
            true
        }
    }


}