package com.alansoft.kapaycote.data.api

import com.alansoft.kapaycote.data.response.BooksSearchResponse
import com.alansoft.kapaycote.utils.SearchSortType
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by LEE MIN KYU on 2021/04/20
 * Copyright © 2021 Dreamus Company. All rights reserved.
 */
interface SearchApi {
    @GET("/v3/search/book")
    suspend fun getSearchBook(
        @Query("query") query: String,
        @Query("sort") sort: SearchSortType,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): BooksSearchResponse
}