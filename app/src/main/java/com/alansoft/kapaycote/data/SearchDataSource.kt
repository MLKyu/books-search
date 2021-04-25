package com.alansoft.kapaycote.data

import com.alansoft.kapaycote.data.response.BooksSearchResponse
import com.alansoft.kapaycote.utils.SearchSortType

/**
 * Created by LEE MIN KYU on 2021/04/26
 * Copyright © 2021 Dreamus Company. All rights reserved.
 */
interface SearchDataSource {
    suspend fun getSearchBooks(
        query: String,
        sort: SearchSortType,
        page: Int,
        size: Int
    ): BooksSearchResponse
}