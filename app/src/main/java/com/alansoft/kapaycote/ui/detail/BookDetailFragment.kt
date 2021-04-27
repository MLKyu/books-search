package com.alansoft.kapaycote.ui.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.alansoft.kapaycote.R
import com.alansoft.kapaycote.data.response.Document
import com.alansoft.kapaycote.databinding.BookDetailFragmentBinding
import com.alansoft.kapaycote.ui.base.BaseFragment
import com.alansoft.kapaycote.utils.setNavigationResult
import com.alansoft.kapaycote.utils.setSelector
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by LEE MIN KYU on 2021/04/20
 * Copyright © 2021 Dreamus Company. All rights reserved.
 */

@AndroidEntryPoint
class BookDetailFragment : BaseFragment<BookDetailFragmentBinding>() {
    private val args: BookDetailFragmentArgs by navArgs()

    override fun getLayoutId(): Int = R.layout.book_detail_fragment

    private var like = false

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.detail_menu_item, menu)
        setLikeView(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        findNavController().navigateUp()
        return super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        bindView(args.document)
    }

    private fun bindView(document: Document) {
        like = document.like
        with(binding) {
            data = document
            progressBar.visibility = View.GONE
        }
    }

    private fun setLikeView(menu: Menu) {
        menu.findItem(R.id.action_like).run {
            setSelector(like)
            setNavigationResult(args.document.setLike(like))
            setOnMenuItemClickListener {
                like = !like
                it.setSelector(like)
                setNavigationResult(args.document.setLike(like))
                true
            }
        }
    }
}