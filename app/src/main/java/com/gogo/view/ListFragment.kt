package com.gogo.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.gogo.R
import com.gogo.databinding.LayoutFragmentListBinding
import com.gogo.entity.ListData
import com.gogo.entity.RowItem
import com.gogo.viewmodel.MainViewModel
import com.gogo.viewmodel.ViewModelFactory
import dagger.android.support.DaggerFragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class ListFragment : DaggerFragment() {
    lateinit var binding: LayoutFragmentListBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: MainViewModel by activityViewModels() { viewModelFactory }

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
        binding.recyclerView.adapter?.let {
            (it as MyAdapter).observeItemClick()
                .subscribe {
                    itemClicked(it)
                }.disposeBy(disposable)
        }

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
        binding.recyclerView.adapter = MyAdapter(requireActivity(), it)
    }

}