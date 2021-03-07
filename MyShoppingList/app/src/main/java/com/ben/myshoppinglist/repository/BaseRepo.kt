package com.ben.myshoppinglist.repository

import com.ben.myshoppinglist.network.ResponseHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

abstract class BaseRepo {

    suspend fun <T> handledApiCall(apiCall: suspend () -> T): ResponseHandler<T> {
        return withContext(Dispatchers.IO) {
            try {
                ResponseHandler.Success(apiCall.invoke())
            }catch (throwable: Throwable) {
                when(throwable) {
                    is HttpException -> {
                        ResponseHandler.Failure(isNetworkError = false, throwable.code(), throwable.response()?.errorBody())
                    }
                    else -> {
                        ResponseHandler.Failure(isNetworkError = true, null, null)
                    }
                }
            }
        }
    }
}