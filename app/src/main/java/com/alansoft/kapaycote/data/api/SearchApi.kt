package com.alansoft.kapaycote.data.api

import com.alansoft.kapaycote.data.response.BooksSearchResponse
import com.alansoft.kapaycote.utils.SearchSortType
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by LEE MIN KYU on 2021/04/20
 * Copyright © 2021 Dreamus Company. All rights reserved.
 */
interface SearchApi {

    /**
     * query	String	검색을 원하는 질의어	O
    sort	String	결과 문서 정렬 방식, accuracy(정확도순) 또는 latest(발간일순), 기본값 accuracy	X
    page	Integer	결과 페이지 번호, 1~50 사이의 값, 기본 값 1	X
    size	Integer	한 페이지에 보여질 문서 수, 1~50 사이의 값, 기본 값 10	X
    target	String	검색 필드 제한
    사용 가능한 값: title(제목), isbn (ISBN), publisher(출판사), person(인명)	X
     */
    @GET("/v3/search/book")
    suspend fun getSearchBook(
        @Query("query") query: String,
        @Query("sort") sort: SearchSortType,
        @Query("page") page: Int,
        @Query("size") size: Int
//        ,
//        @Query("target") target: String
    ): Response<BooksSearchResponse>
}