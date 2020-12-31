package com.gogo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.gogo.entity.ListData
import com.gogo.entity.Response
import com.gogo.entity.RowItem
import com.gogo.entity.State
import com.gogo.repo.SearchResultRepository
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val searchResultRepository: SearchResultRepository
) : ViewModel() {

    lateinit var listDataSourceFactory: ListDataSourceFactory
    val pageSize = 5
    lateinit var dataList: LiveData<PagedList<RowItem>>

    init {
        listDataSourceFactory = ListDataSourceFactory(searchResultRepository)
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setEnablePlaceholders(false)
            .build()
        dataList = LivePagedListBuilder<Int, RowItem>(listDataSourceFactory, config).build()
    }

    private val compositeDisposable = CompositeDisposable()

    private val itemClickPublisher = BehaviorSubject.create<RowItem>()

    private val dataListSubject = BehaviorSubject.create<ListData>()

    private val errorPublisher = BehaviorSubject.create<Exception>()

    private val statePublisher = BehaviorSubject.create<State>()

    fun observeItemClick(): Observable<RowItem> = itemClickPublisher

    fun observeResultList(): Observable<ListData> = dataListSubject

    fun observeState(): Observable<State> = statePublisher

    fun fetchList(query: String) {
        compositeDisposable.add(searchResultRepository.loadData(query).subscribe {
            handleSearchResult(it) }
        )
    }

    private fun handleSearchResult(response: Response<ListData>) {
        when (response) {
            is Response.Success -> dataListSubject.onNext(response.data)
            is Response.Failure -> errorPublisher.onNext(response.exception)
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun itemClicked(rowItem: RowItem) {
        itemClickPublisher.onNext(rowItem)
    }
}