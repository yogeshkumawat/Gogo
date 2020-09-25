package com.gogo.repo

import com.gogo.entity.ListData
import com.gogo.entity.RowItem
import io.reactivex.Observable

object Repository {

    fun loadData() : Observable<ListData> {
        return Observable.just(ListData(listOf(RowItem("hi"), RowItem("Hello"), RowItem("Bye"))))
    }
}