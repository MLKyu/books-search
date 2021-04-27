package com.alansoft.kapaycote.ui.search

import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.DiffUtil
import com.alansoft.kapaycote.R
import com.alansoft.kapaycote.data.response.Document
import com.alansoft.kapaycote.databinding.BooksItemBinding
import com.alansoft.kapaycote.ui.base.BaseListAdapter

/**
 * Created by LEE MIN KYU on 2021/04/26
 * Copyright © 2021 Dreamus Company. All rights reserved.
 */
class SearchListAdapter(
    private val itemCallback: ((Pair<Document, Int>) -> Unit)?
) : BaseListAdapter<Document>(DiffCallback()) {
    override fun createView(viewType: Int): Int {
        return R.layout.books_item
    }

    override fun getViewType(item: Document): Int {
        return R.layout.books_item.hashCode()
    }

    override fun bind(binding: ViewDataBinding, position: Int) {
        if (binding is BooksItemBinding) {
            binding.setVariable(BR.item, getItem(position))
        }

        binding.root.setOnClickListener {
            itemCallback?.invoke(Pair(getItem(position), position))
        }
    }
}

private class DiffCallback : DiffUtil.ItemCallback<Document>() {

    override fun areItemsTheSame(oldItem: Document, newItem: Document): Boolean {
        return oldItem.isbn == newItem.isbn
    }

    override fun areContentsTheSame(oldItem: Document, newItem: Document): Boolean {
        return oldItem == newItem
    }
}
