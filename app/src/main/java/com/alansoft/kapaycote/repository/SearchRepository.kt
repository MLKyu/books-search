package com.alansoft.kapaycote.repository

import androidx.lifecycle.liveData
import com.alansoft.kapaycote.data.Resource
import com.alansoft.kapaycote.data.SearchDataSource
import com.alansoft.kapaycote.data.response.BooksSearchResponse
import com.alansoft.kapaycote.utils.FIRST_PAGE
import com.alansoft.kapaycote.utils.PAGE_SIZE
import com.alansoft.kapaycote.utils.SearchSortType
import javax.inject.Inject

/**
 * Created by LEE MIN KYU on 2021/04/24
 * Copyright © 2021 Dreamus Company. All rights reserved.
 */
class SearchRepository @Inject constructor(
    private val searchDataSource: SearchDataSource
) {

    fun getSearchBooks(query: String, page: Int = FIRST_PAGE) =
        liveData<Resource<BooksSearchResponse>> {
            emit(Resource.loading())
            val response = searchDataSource.getSearchBooks(
                query, SearchSortType.RECENCY, page, PAGE_SIZE
            )

            when (response.status){
                Resource.Status.SUCCESS -> {
//                    responseStatus.data?.let {
//                        emit(Resource.success(it))
//                    }
                }
                Resource.Status.ERROR -> {
//                    responseStatus.message?.let {
//                        emit(Resource.error(it))
//                    }
                }
                else -> {
                    // ignore
                }
            }
        }
}