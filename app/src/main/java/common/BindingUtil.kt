package common

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import view.ListAdapter

object BindingUtil {

    @JvmStatic
    @BindingAdapter("xyz:adapter")
    fun setAdapter(recyclerView: RecyclerView, adapter: ListAdapter) {
        recyclerView.adapter = adapter
    }
}