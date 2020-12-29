package com.gogo.viewmodel

import androidx.lifecycle.ViewModel
import com.gogo.entity.ListData
import com.gogo.entity.Response
import com.gogo.entity.RowItem
import com.gogo.repo.SearchResultRepository
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val searchResultRepository: SearchResultRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val itemClickPublisher = BehaviorSubject.create<RowItem>()

    private val dataListSubject = BehaviorSubject.create<ListData>()

    private val errorPublisher = BehaviorSubject.create<Exception>()

    fun observeItemClick(): Observable<RowItem> = itemClickPublisher

    fun observeResultList(): Observable<ListData> = dataListSubject

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