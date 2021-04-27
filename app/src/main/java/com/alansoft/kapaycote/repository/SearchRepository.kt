package com.alansoft.kapaycote.repository

import com.alansoft.kapaycote.data.Result
import com.alansoft.kapaycote.data.SearchCacheDataSource
import com.alansoft.kapaycote.data.SearchRemoteDataSource
import com.alansoft.kapaycote.data.response.BooksSearchResponse
import com.alansoft.kapaycote.utils.FIRST_PAGE
import com.alansoft.kapaycote.utils.PAGE_SIZE
import com.alansoft.kapaycote.utils.SearchSortType
import kotlinx.coroutines.flow.*
import java.io.IOException
import javax.inject.Inject

/**
 * Created by LEE MIN KYU on 2021/04/24
 * Copyright © 2021 Dreamus Company. All rights reserved.
 */
class SearchRepository @Inject constructor(
    private val cache: SearchCacheDataSource,
    private val remote: SearchRemoteDataSource
) {
    fun getSearchBooks(
        query: String,
        page: Int = FIRST_PAGE
    ): Flow<Result<BooksSearchResponse>> = flow {
        emit(Result.loading())

        val response: BooksSearchResponse

        emit(if (cache.isExistAndFresh(query, page)) {
            response = cache.getSearchResponse(query, page)
            Result.success(response)
        } else {
            response = remote.getSearchBooks(
                query, SearchSortType.RECENCY, page,
                PAGE_SIZE
            )

            if (response.documents.isNullOrEmpty()) {
                Result.empty()
            } else {
                Result.pushAndSuccess(response) {
                    cache.pushSearchResponse(query, page, it)
                    it.fromData(query, page)
                }
            }
        })
    }.retry(2) { cause ->
        cause is IOException
    }.catch { e ->
        emit(Result.error(e))
    }
}