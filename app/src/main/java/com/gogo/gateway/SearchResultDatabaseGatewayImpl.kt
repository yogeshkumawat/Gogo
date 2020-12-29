package com.gogo.gateway

import com.gogo.entity.ListData
import com.gogo.entity.Response
import io.reactivex.Observable
import java.lang.Exception
import javax.inject.Inject

class SearchResultDatabaseGatewayImpl @Inject constructor(): SearchResultDatabaseGateway {

    override fun load(): Observable<Response<ListData>> {
        return Observable.just(Response.Failure(Exception("Not implemented yet")))
    }
}