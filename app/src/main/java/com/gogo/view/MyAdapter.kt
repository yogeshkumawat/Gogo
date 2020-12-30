package com.gogo.view

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gogo.R
import com.gogo.databinding.LayoutRowItemBinding
import com.gogo.entity.ListData
import com.gogo.entity.RowItem
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import com.gogo.view.MyAdapter.MyViewHolder

class MyAdapter(
    private val context: Context
) : RecyclerView.Adapter<MyViewHolder>() {

    private val itemClickPublisher = PublishSubject.create<RowItem>()

    fun observeItemClick(): Observable<RowItem> = itemClickPublisher

    var listData: ListData? = null

    lateinit var binding: LayoutRowItemBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(context)
        binding = DataBindingUtil.inflate(inflater, R.layout.layout_row_item, parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listData?.items?.size?:0
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
         listData?.let {
             Log.v("BOSS","Name ${it.items[position].login} Position $position")
             holder.binding.title.text = it.items[position].login
             holder.itemView.tag = it.items[position]

            holder.itemView.setOnClickListener {
                val item = it.tag as RowItem
                itemClickPublisher.onNext(item)
            }
        }
    }

    class MyViewHolder(val binding: LayoutRowItemBinding) : RecyclerView.ViewHolder(binding.root) {
    }
}