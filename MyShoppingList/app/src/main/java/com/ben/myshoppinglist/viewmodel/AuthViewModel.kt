package com.ben.myshoppinglist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ben.myshoppinglist.model.LoginRequest
import com.ben.myshoppinglist.model.LoginResponse
import com.ben.myshoppinglist.network.ResponseHandler
import com.ben.myshoppinglist.repository.AuthRepo
import kotlinx.coroutines.launch

class AuthViewModel(private val authRepo: AuthRepo): ViewModel() {

    private val _loginResponse = MutableLiveData<ResponseHandler<LoginResponse>>()
    val loginResponse: LiveData<ResponseHandler<LoginResponse>>
        get() = _loginResponse



    fun onLogin(loginRequest: LoginRequest) = viewModelScope.launch {
        _loginResponse.value = authRepo.onLogin(loginRequest)
    }

    fun onSaveAuthToken(accessToken: String) = viewModelScope.launch {
        authRepo.onSaveAuthToken(accessToken)
    }

}