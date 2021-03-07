package com.ben.myshoppinglist.view.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.ben.myshoppinglist.R
import com.ben.myshoppinglist.databinding.FragmentLoginBinding
import com.ben.myshoppinglist.model.LoginRequest
import com.ben.myshoppinglist.model.UserPreferences
import com.ben.myshoppinglist.network.ResponseHandler
import com.ben.myshoppinglist.repository.AuthRepo
import com.ben.myshoppinglist.viewmodel.AuthViewModel
import kotlinx.coroutines.launch

// TODO: Check if Token is present. If not -> Show login Fragment, else show HomeFragment
class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepo>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.onLogin(LoginRequest("test6@test.com", "testtest"))
        viewModel.loginResponse.observe(viewLifecycleOwner) {
            when(it) {
                is ResponseHandler.Success -> {
                    viewModel.onSaveAuthToken(it.value.accessToken)
                }
                is ResponseHandler.Failure -> Toast.makeText(requireContext(), "Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onBindFragment(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentLoginBinding = FragmentLoginBinding.inflate(inflater, container, false)

    override fun onBindRepository(): AuthRepo = AuthRepo(myShoppingListNetwork.initializeRetrofit(), userPreferences)

    override fun onBindViewModel(): Class<AuthViewModel> = AuthViewModel::class.java
}