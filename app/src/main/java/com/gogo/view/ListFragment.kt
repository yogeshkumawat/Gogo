package com.gogo.view

import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.gogo.R
import com.gogo.databinding.LayoutFragmentListBinding
import com.gogo.databinding.LayoutFragmentListSearchBinding
import com.gogo.entity.ListData
import com.gogo.entity.RowItem
import com.gogo.viewmodel.MainViewModel
import com.gogo.viewmodel.ViewModelFactory
import dagger.android.support.DaggerFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class ListFragment : DaggerFragment() {
    lateinit var binding: LayoutFragmentListSearchBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: MainViewModel by activityViewModels { viewModelFactory }

    private val disposable = CompositeDisposable()

    private lateinit var adapter: MyAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.layout_fragment_list_search, container, false
        )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //observeSearchBoxQuery()
        adapter = MyAdapter(requireActivity())
        binding.recyclerView.adapter = adapter
        addSearchButtonClick()
        observeSearchResult()
        observeItemClick()
    }

    private fun addSearchButtonClick() {
        binding.searchButton1.setOnClickListener {
            fetchResult(it as Button)
        }
        binding.searchButton2.setOnClickListener {
            fetchResult(it as Button)
        }
        binding.searchButton3.setOnClickListener {
            fetchResult(it as Button)
        }
        binding.searchButton4.setOnClickListener {
            fetchResult(it as Button)
        }
    }

    private fun fetchResult(it: Button) {
        fetchSearchResults(it.text.toString())
    }

    /*private fun observeSearchBoxQuery() {
        binding.searchBox.addTextChangedListener(object :
            TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                *//*runnable?.let { handler.removeCallbacks(it) }
                runnable = Runnable { fetchSearchResults(s?.toString()) }
                Handler().postDelayed({ runnable }, 2000)*//*
                Handler().postDelayed({
                    fetchSearchResults(s?.toString())
                }, 500)
            }
        })
    }*/

    private fun fetchSearchResults(query: String?) {
        if(!query.isNullOrEmpty())
            viewModel.fetchList(query)
    }

    private fun observeItemClick() {
        adapter.observeItemClick().subscribe {
            itemClicked(it)
        }.disposeBy(disposable)
    }

    private fun itemClicked(rowItem: RowItem) {
        viewModel.itemClicked(rowItem)
    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }

    private fun observeSearchResult() {
        viewModel.observeResultList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                setData(it)
            }
            .disposeBy(disposable)
    }

    private fun Disposable.disposeBy(compositeDisposable: CompositeDisposable) {
        compositeDisposable.add(this)
    }

    private fun setData(it: ListData) {
        adapter.listData = it
        adapter.notifyDataSetChanged()
    }

}