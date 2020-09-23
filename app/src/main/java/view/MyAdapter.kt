package view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gogo.R
import com.gogo.databinding.LayoutRowItemBinding
import entity.ListData
import entity.RowItem
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import view.MyAdapter.MyViewHolder

class MyAdapter(
    private val context: Context,
    private val listData: ListData
) : RecyclerView.Adapter<MyViewHolder>() {

    private val itemClickPublisher = PublishSubject.create<RowItem>()

    fun observeItemClick(): Observable<RowItem> = itemClickPublisher

    lateinit var binding: LayoutRowItemBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(context)
        binding = DataBindingUtil.inflate(inflater, R.layout.layout_row_item, parent, false)
        return MyViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return listData.list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        binding.item = listData.list.get(position)
        holder.itemView.setTag(binding.item)

        holder.itemView.setOnClickListener {
            val item = it.getTag() as RowItem
            itemClickPublisher.onNext(item)
        }

        binding.executePendingBindings()
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}