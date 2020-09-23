package com.gogo.repo

import entity.ListData
import entity.RowItem
import io.reactivex.Observable

object Repository {

    fun loadData() : Observable<ListData> {
        return Observable.just(ListData(listOf(RowItem("hi"), RowItem("Hello"), RowItem("Bye"))))
    }
}