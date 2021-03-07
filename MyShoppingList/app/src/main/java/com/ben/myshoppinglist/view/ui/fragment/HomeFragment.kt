package com.ben.myshoppinglist.view.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ben.myshoppinglist.R
import com.ben.myshoppinglist.databinding.FragmentHomeBinding
import com.ben.myshoppinglist.network.ResponseHandler
import com.ben.myshoppinglist.repository.UserRepo
import com.ben.myshoppinglist.viewmodel.HomeViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding, UserRepo>() {

    override fun onBindViewModel(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onFetchCurrentUser()
        viewModel.user.observe(viewLifecycleOwner) {
            when(it) {
                is ResponseHandler.Success -> Log.d("Test", "${it.value}")
                is ResponseHandler.Failure -> Log.d("Test", "${it.responseBody}")
                is ResponseHandler.Loading -> Log.d("Test", "Loading...")
            }
        }
    }

    private fun updateUI() {
        with(binding) {

        }
    }

    override fun onBindRepository(): UserRepo {
        val accessToken = runBlocking {
            userPreferences.accessTokenFlow.first()
        }
        val api = myShoppingListNetwork.initializeRetrofit(accessToken)
        return UserRepo(api)
    }

    override fun onBindFragment(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)

}