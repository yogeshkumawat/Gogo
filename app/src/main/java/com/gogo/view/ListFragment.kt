package com.gogo.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.gogo.R
import com.gogo.databinding.LayoutFragmentListBinding
import com.gogo.entity.ListData
import com.gogo.entity.RowItem
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import com.gogo.viewmodel.MainViewModel

class ListFragment : Fragment() {
    private val viewModel: MainViewModel by activityViewModels()
    lateinit var binding: LayoutFragmentListBinding

    private val disposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.layout_fragment_list, container, false
        )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeViewModel()
        observeItemClick()
    }

    private fun observeItemClick() {
        binding.adapter?.observeItemClick()
            ?.subscribe {
                itemClicked(it)
            }
            ?.disposeBy(disposable)
    }

    private fun itemClicked(rowItem: RowItem) {
        viewModel.itemClicked(rowItem)
    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }

    private fun observeViewModel() {
        viewModel.getList()
            .subscribe {
                setData(it)
            }
            .disposeBy(disposable)
    }

    private fun Disposable.disposeBy(compositeDisposable: CompositeDisposable) {
        compositeDisposable.add(this)
    }

    private fun setData(it: ListData) {
        binding.adapter = MyAdapter(requireActivity(), it)
    }

}