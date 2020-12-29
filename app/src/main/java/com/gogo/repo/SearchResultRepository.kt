package com.gogo.repo

import com.gogo.di.AppScope
import com.gogo.entity.ListData
import com.gogo.entity.Response
import com.gogo.entity.RowItem
import com.gogo.gateway.SearchResultDatabaseGateway
import com.gogo.gateway.SearchResultNetworkGateway
import io.reactivex.Observable
import javax.inject.Inject

@AppScope
class SearchResultRepository @Inject constructor(
    private val searchResultNetworkGateway: SearchResultNetworkGateway,
    private val searchResultDatabaseGateway: SearchResultDatabaseGateway
) {

    fun loadData(query: String): Observable<Response<ListData>> {
        return searchResultDatabaseGateway.load().flatMap {
            handleDatabaseResult(it)
        }
    }

    private fun handleDatabaseResult(response: Response<ListData>): Observable<Response<ListData>> {
        return when(response) {
            is Response.Success -> Observable.just(Response.Success(response.data))
            is Response.Failure -> fetchFromNetwork()
        }
    }

    private fun fetchFromNetwork(): Observable<Response<ListData>> =
        searchResultNetworkGateway.load()
}