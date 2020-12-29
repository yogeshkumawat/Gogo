package com.gogo.gateway

import com.gogo.entity.ListData
import com.gogo.entity.Response
import com.gogo.entity.RowItem
import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SearchResultNetworkGatewayImpl @Inject constructor(): SearchResultNetworkGateway {

    override fun load(): Observable<Response<ListData>> {
        return Observable.timer(4, TimeUnit.SECONDS).flatMap {
            Observable.just(Response.Success(createDummyData()))
        }
    }

    private fun createDummyData(): ListData {
        return ListData(listOf(RowItem("hi"), RowItem("Dummy"), RowItem("Bye")))
    }
}