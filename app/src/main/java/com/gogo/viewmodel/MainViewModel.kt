package com.gogo.viewmodel

import androidx.lifecycle.ViewModel
import com.gogo.repo.Repository
import com.gogo.entity.ListData
import com.gogo.entity.RowItem
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class MainViewModel : ViewModel() {
    private val repository = Repository

    private val itemClickPublisher = BehaviorSubject.create<RowItem>()

    fun observeItemClick(): Observable<RowItem> = itemClickPublisher

    fun getList():Observable<ListData>{
        return repository.loadData();
    }

    fun itemClicked(rowItem: RowItem) {
        itemClickPublisher.onNext(rowItem)
    }
}