package com.ben.myshoppinglist.view.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.ben.myshoppinglist.model.UserPreferences
import com.ben.myshoppinglist.network.MyShoppingListNetwork
import com.ben.myshoppinglist.repository.BaseRepo
import com.ben.myshoppinglist.viewmodel.ViewModelFactory
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

abstract class BaseFragment<VM: ViewModel, VB: ViewBinding, R: BaseRepo>: Fragment() {

    protected lateinit var userPreferences: UserPreferences
    protected lateinit var binding: VB
    protected lateinit var viewModel: VM
    protected val myShoppingListNetwork = MyShoppingListNetwork


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        userPreferences = UserPreferences(requireContext())
        binding = onBindFragment(inflater, container, savedInstanceState)
        val viewModelFactory = ViewModelFactory(onBindRepository())
        viewModel = ViewModelProvider(this, viewModelFactory).get(onBindViewModel())
        lifecycleScope.launch {
            userPreferences.accessTokenFlow.first()
        }
        return binding.root
    }

    abstract fun onBindViewModel() : Class<VM>
    abstract fun onBindFragment(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): VB
    abstract fun onBindRepository(): R
}