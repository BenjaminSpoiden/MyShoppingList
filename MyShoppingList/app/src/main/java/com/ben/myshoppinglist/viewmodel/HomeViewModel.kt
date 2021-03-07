package com.ben.myshoppinglist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ben.myshoppinglist.model.UserResponse
import com.ben.myshoppinglist.network.ResponseHandler
import com.ben.myshoppinglist.repository.UserRepo
import kotlinx.coroutines.launch

class HomeViewModel(private val userRepo: UserRepo): ViewModel() {

    private val _user: MutableLiveData<ResponseHandler<UserResponse>> = MutableLiveData()
    val user: LiveData<ResponseHandler<UserResponse>>
        get() = _user

    fun onFetchCurrentUser() = viewModelScope.launch {
        _user.value = ResponseHandler.Loading
        _user.value = userRepo.onFetchCurrentUser()
    }
}