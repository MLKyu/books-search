package com.alansoft.kapaycote.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

/**
 * Created by LEE MIN KYU on 2021/04/20
 * Copyright © 2021 Dreamus Company. All rights reserved.
 */
data class BooksSearchResponse(
    val meta: Meta?,
    val documents: List<Document?>?
) {
    fun fromData(query: String, page: Int): BooksSearchResponse {
        return BooksSearchResponse(
            Meta(
                query,
                page,
                meta?.totalCount ?: -1,
                meta?.pageableCount ?: -1,
                meta?.isEnd ?: false
            ), documents
        )
    }
}

data class Meta(
    val query: String,
    val page: Int,
    @SerializedName("total_count")
    var totalCount: Int,
    @SerializedName("pageable_count")
    var pageableCount: Int,
    @SerializedName("is_end")
    var isEnd: Boolean
)

@Parcelize
data class Document(
    val title: String?,
    val contents: String?,
    val url: String?,
    val isbn: String?,
    val datetime: Date?,
    val authors: List<String?>?,
    val publisher: String?,
    val translators: List<String?>?,
    val price: Int,
    @SerializedName("sale_price")
    val salePrice: Int,
    val thumbnail: String?,
    val status: String?,
    var like: Boolean = false,
    var position: Int
) : Parcelable {

    fun setLike(like: Boolean): Document {
        return this.apply {
            this.like = like
        }
    }

    fun setPostion(postion: Int): Document {
        return this.apply {
            this.position = position
        }
    }
}