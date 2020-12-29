package com.gogo.viewmodel

import androidx.lifecycle.ViewModel
import com.gogo.entity.ListData
import com.gogo.entity.RowItem
import com.gogo.repo.Repository
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val itemClickPublisher = BehaviorSubject.create<RowItem>()

    private val dataListSubject = BehaviorSubject.create<ListData>()

    fun observeItemClick(): Observable<RowItem> = itemClickPublisher

    fun observeResultList(): Observable<ListData> {
        return dataListSubject
    }

    fun fetchList(query: String) {
        repository.loadData(query).subscribe {
            dataListSubject.onNext(it)
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