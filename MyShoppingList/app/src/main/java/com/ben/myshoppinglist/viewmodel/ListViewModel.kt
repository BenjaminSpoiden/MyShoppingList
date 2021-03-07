package com.ben.myshoppinglist.viewmodel


import android.util.Log
import androidx.lifecycle.*
import com.ben.myshoppinglist.model.LoginRequest
import com.ben.myshoppinglist.model.LoginResponse
import com.ben.myshoppinglist.network.MyShoppingListNetwork
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListViewModel: ViewModel() {

    private val _loginResponse = MutableLiveData<LoginResponse?>()
    val loginResponse: LiveData<LoginResponse?>
        get() = _loginResponse

    init {
        onLogin()
    }


    private fun onLogin() {

    }
}