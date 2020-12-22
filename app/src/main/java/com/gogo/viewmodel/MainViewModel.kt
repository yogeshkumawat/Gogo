package com.gogo.viewmodel

import androidx.lifecycle.ViewModel
import com.gogo.GogoApp
import com.gogo.repo.Repository
import com.gogo.entity.ListData
import com.gogo.entity.RowItem
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class MainViewModel @Inject constructor() : ViewModel() {

    var repository: Repository = Repository()

    private val itemClickPublisher = BehaviorSubject.create<RowItem>()

    fun observeItemClick(): Observable<RowItem> = itemClickPublisher

    init {
//        GogoApp.appInstance.applicationInjector().inject(this)
    }

    fun getList():Observable<ListData>{
        return repository.loadData()
    }

    fun itemClicked(rowItem: RowItem) {
        itemClickPublisher.onNext(rowItem)
    }
}