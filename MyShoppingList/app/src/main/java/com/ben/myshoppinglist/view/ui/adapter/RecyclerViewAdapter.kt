package com.ben.myshoppinglist.view.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ben.myshoppinglist.R

class RecyclerViewAdapter(private val mockList: List<String>): RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {

    override fun getItemCount(): Int = mockList.size

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.textView.text = mockList[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return RecyclerViewHolder(v)
    }

    inner class RecyclerViewHolder(v: View): RecyclerView.ViewHolder(v) {
        val textView: TextView = v.findViewById(R.id.some_text)
    }
}