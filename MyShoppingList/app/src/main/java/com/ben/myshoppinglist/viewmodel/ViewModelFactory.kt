package com.ben.myshoppinglist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ben.myshoppinglist.repository.AuthRepo
import com.ben.myshoppinglist.repository.BaseRepo
import com.ben.myshoppinglist.repository.UserRepo
import java.lang.IllegalArgumentException

class ViewModelFactory(private val baseRepo: BaseRepo): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(baseRepo as AuthRepo) as T
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(baseRepo as UserRepo) as T
            else -> throw IllegalArgumentException("VM Not found")
        }
    }
}