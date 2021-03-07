package com.ben.myshoppinglist.repository

import com.ben.myshoppinglist.model.LoginRequest
import com.ben.myshoppinglist.model.UserPreferences
import com.ben.myshoppinglist.network.MyShoppingListService

class AuthRepo(private val networkService: MyShoppingListService, private val userPreferences: UserPreferences): BaseRepo() {

    suspend fun onLogin(loginRequest: LoginRequest) = handledApiCall {
        networkService.onLogin(loginRequest)
    }

    suspend fun onSaveAuthToken(accessToken: String) = userPreferences.onSaveAuthToken(accessToken)
}