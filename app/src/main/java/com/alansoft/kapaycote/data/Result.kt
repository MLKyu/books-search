package com.alansoft.kapaycote.data

import java.io.IOException

/**
 * Created by LEE MIN KYU on 2021/04/26
 * Copyright © 2021 Dreamus Company. All rights reserved.
 */
sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val exception: Throwable) : Result<Nothing>() {
        val isNetworkError: Boolean get() = exception is IOException
    }

    object Empty : Result<Nothing>()
    object Loading : Result<Nothing>()
    companion object {
        fun <T> success(data: T) = Success(data)
        fun error(exception: Throwable) = Error(exception)
        fun empty() = Empty
        fun loading() = Loading
        fun <T, R> pushAndSuccess(data: T, block: (data: T) -> R): Result<R> {
            return Success(block(data))
        }
    }
}
