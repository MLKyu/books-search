package com.alansoft.kapaycote.ui.search

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.alansoft.kapaycote.R
import com.alansoft.kapaycote.data.response.Document
import com.alansoft.kapaycote.ui.base.BaseListAdapter

/**
 * Created by LEE MIN KYU on 2021/04/24
 * Copyright © 2021 Dreamus Company. All rights reserved.
 */
//class BooksListAdapter(private val itemCallback: ((Document) -> Unit)?) :
//    BaseListAdapter<Document>(object :
//        DiffUtil.ItemCallback<Document>() {
//        override fun areItemsTheSame(
//            oldItem: Document,
//            newItem: Document
//        ): Boolean {
//            return oldItem.datetime == newItem.datetime
//        }
//
//        override fun areContentsTheSame(
//            oldItem: Document,
//            newItem: Document
//        ): Boolean {
//            return oldItem.hashCode() == newItem.hashCode()
//        }
//    }) {
//
//    override fun createView(viewType: Int): Int {
//        return when (viewType) {
//            ViewType.IMAGE.hashCode() -> R.layout.image_item
//            else -> R.layout.vclip_item
//        }
//    }
//
//    override fun bind(binding: ViewDataBinding, item: Document) {
////        if (item is ImageDocuments && binding is ImageItemBinding) {
////            binding.run {
////                description.text = item.doc_url
////                title.text = item.collection
////                subTitle.text = item.datetime.toString()
////                Glide.with(root)
////                    .load(item.thumbnail_url)
////                    .transform(CenterCrop())
////                    .into(thumbnail)
////            }
////        } else if (item is VClipDocuments && binding is VclipItemBinding) {
////            binding.run {
////                description.text = item.title
////                author.text = item.author
////                subTitle.text = item.datetime.toString()
////                Glide.with(root)
////                    .load(item.thumbnail)
////                    .transform(CenterCrop())
////                    .into(thumbnail)
////            }
////        }
//
//        binding.root.setOnClickListener {
//            itemCallback?.invoke(item)
//        }
//    }
//
//    override fun getViewType(item: Document): Int {
//    }
//}