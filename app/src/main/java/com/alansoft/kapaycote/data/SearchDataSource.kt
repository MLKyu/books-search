package com.alansoft.kapaycote.data

import com.alansoft.kapaycote.data.api.SearchApi
import com.alansoft.kapaycote.utils.SearchSortType
import javax.inject.Inject

/**
 * Created by LEE MIN KYU on 2021/04/24
 * Copyright © 2021 Dreamus Company. All rights reserved.
 */
class SearchDataSource @Inject constructor(private val searchApi: SearchApi) : BaseDataSource() {
    suspend fun getSearchBooks(
        query: String,
        sort: SearchSortType,
        page: Int,
        size: Int
//        ,
//        target: String
    ) = getResult {
        searchApi.getSearchBook(
            query, sort, page, size
//        , target
        )
    }
}