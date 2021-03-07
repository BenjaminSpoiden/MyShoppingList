package com.ben.myshoppinglist.network

import okhttp3.ResponseBody

sealed class ResponseHandler<out T> {
    data class Success<out T>(val value: T): ResponseHandler<T>()
    data class Failure(
        val isNetworkError: Boolean,
        val statusCode: Int?,
        val responseBody: ResponseBody?
    ): ResponseHandler<Nothing>()
    object Loading: ResponseHandler<Nothing>()
}
