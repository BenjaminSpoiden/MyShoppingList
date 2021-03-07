package com.ben.myshoppinglist.repository

import com.ben.myshoppinglist.network.MyShoppingListService

class UserRepo(private val networkService: MyShoppingListService): BaseRepo() {

    suspend fun onFetchCurrentUser() = handledApiCall {
        networkService.onFetchCurrentUser()
    }
}