package com.alansoft.kapaycote.di

import com.alansoft.kapaycote.data.SearchCacheDataSource
import com.alansoft.kapaycote.data.SearchRemoteDataSource
import com.alansoft.kapaycote.data.api.SearchApi
import com.alansoft.kapaycote.repository.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by LEE MIN KYU on 2021/04/26
 * Copyright © 2021 Dreamus Company. All rights reserved.
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideSearchRemoteDataSource(searchApi: SearchApi) =
        SearchRemoteDataSource(searchApi)

    @Singleton
    @Provides
    fun provideSearchRepository(
        cache: SearchCacheDataSource,
        remote: SearchRemoteDataSource
    ) =
        SearchRepository(cache, remote)
}