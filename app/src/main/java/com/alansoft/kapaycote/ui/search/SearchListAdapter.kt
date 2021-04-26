package com.alansoft.kapaycote.ui.search

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.alansoft.kapaycote.R
import com.alansoft.kapaycote.data.response.Document
import com.alansoft.kapaycote.databinding.BooksItemBinding
import com.alansoft.kapaycote.ui.base.BaseListAdapter
import com.alansoft.kapaycote.utils.loadWithThumbnail

/**
 * Created by LEE MIN KYU on 2021/04/26
 * Copyright © 2021 Dreamus Company. All rights reserved.
 */
class SearchListAdapter(
    private val itemCallback: ((Document) -> Unit)?
) : BaseListAdapter<Document>(DiffCallback()) {

    override fun createView(viewType: Int): Int {
        return R.layout.books_item
    }

    override fun getViewType(item: Document): Int {
        return R.layout.books_item.hashCode()
    }

    override fun bind(binding: ViewDataBinding, item: Document) {
        if (binding is BooksItemBinding) {
            binding.bookImg.loadWithThumbnail(item.thumbnail)
            binding.bookDate.text = item.datetime
            binding.bookDes.text = item.contents
            binding.bookName.text = item.title
            binding.bookPrice.text = item.salePrice.toString()
//            binding.bookLike
        }

        binding.root.setOnClickListener {
            itemCallback?.invoke(item)
        }
    }
}

private class DiffCallback : DiffUtil.ItemCallback<Document>() {

    override fun areItemsTheSame(oldItem: Document, newItem: Document): Boolean {
        return oldItem.title == newItem.title && oldItem.url == newItem.url
    }

    override fun areContentsTheSame(oldItem: Document, newItem: Document): Boolean {
        return oldItem == newItem
    }
}
