package com.alansoft.kapay_cote.di

import com.alansoft.kapay_cote.data.api.ApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by LEE MIN KYU on 2021/04/20
 * Copyright © 2021 Dreamus Company. All rights reserved.
 */
//@Module
//object DataModule {
//    @JvmStatic
//    @Singleton
//    @Provides
//    fun provideApiService(): ApiService {
//        val baseUrl = "https://dapi.kakao.com/"
//        val retrofit: Retrofit = Retrofit.Builder()
//            .baseUrl(baseUrl)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//        return retrofit.create(ApiService::class.java)
//    }
//
//}