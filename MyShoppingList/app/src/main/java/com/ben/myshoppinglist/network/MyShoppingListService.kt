package com.ben.myshoppinglist.network

import com.ben.myshoppinglist.model.LoginRequest
import com.ben.myshoppinglist.model.LoginResponse
import com.ben.myshoppinglist.model.UserResponse
import retrofit2.http.*

interface MyShoppingListService {

    @POST("/login")
    suspend fun onLogin(@Body request: LoginRequest): LoginResponse

    @GET("/currentUser")
    suspend fun onFetchCurrentUser(): UserResponse

}