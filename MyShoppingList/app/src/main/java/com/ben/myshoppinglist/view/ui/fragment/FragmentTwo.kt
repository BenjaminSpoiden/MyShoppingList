package com.ben.myshoppinglist.view.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ben.myshoppinglist.R
import com.ben.myshoppinglist.view.ui.adapter.RecyclerViewAdapter
import com.ben.myshoppinglist.viewmodel.SaveViewModel

class FragmentTwo : Fragment() {

    private val saveViewModel by navGraphViewModels<SaveViewModel>(R.id.nav_graph)
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private lateinit var recyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_two, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.rv_testing)
        recyclerViewAdapter = RecyclerViewAdapter(mockList())

        recyclerView.apply {
            this.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            this.adapter = recyclerViewAdapter
        }
    }

    private fun mockList(): List<String> {
        val arrayList = ArrayList<String>()

        for(i in 0..50) {
            arrayList.add("$i")
        }

        return arrayList
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        saveViewModel.recyclerViewState?.let {
            recyclerView.layoutManager?.onRestoreInstanceState(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        saveViewModel.recyclerViewState = recyclerView.layoutManager?.onSaveInstanceState()
    }
}