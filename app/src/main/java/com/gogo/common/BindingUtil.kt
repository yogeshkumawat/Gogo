package com.gogo.common

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gogo.view.MyAdapter

object BindingUtil {

    @JvmStatic
    @BindingAdapter("xyz:adapter")
    fun setAdapter(recyclerView: RecyclerView, adapter: MyAdapter) {
        recyclerView.adapter = adapter
    }
}