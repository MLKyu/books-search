package com.alansoft.kapaycote.di

import com.alansoft.kapaycote.data.api.SearchApi
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by LEE MIN KYU on 2021/04/20
 * Copyright © 2021 Dreamus Company. All rights reserved.
 */
@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Singleton
    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        val chainInterceptor = { chain: Interceptor.Chain ->
            chain.proceed(
                chain.request().newBuilder()
                    .header("Authorization", "KakaoAK 240c100923000a471fcd6d5bff20dec2")
                    .build()
            )
        }
        return OkHttpClient.Builder()
            .addInterceptor(chainInterceptor)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .readTimeout(120, TimeUnit.SECONDS)
            .connectTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun providesRetrofitBuilder(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://dapi.kakao.com/")
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
//                            .setDateFormat("")
                        .create()
                )
            )
            .client(client)
            .build()

    @Singleton
    @Provides
    fun providesKakaoSearchApi(retrofit: Retrofit): SearchApi =
        retrofit.create(SearchApi::class.java)
}