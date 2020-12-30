package com.gogo.repo

import com.gogo.di.AppScope
import com.gogo.entity.ListData
import com.gogo.entity.Response
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
            handleDatabaseResult(query, it)
        }
    }

    private fun handleDatabaseResult(
        query: String,
        response: Response<ListData>
    ): Observable<Response<ListData>> {
        return when (response) {
            is Response.Success -> Observable.just(Response.Success(response.data))
            is Response.Failure -> fetchFromNetwork(query)
        }
    }

    private fun fetchFromNetwork(query: String): Observable<Response<ListData>> =
        searchResultNetworkGateway.load(query)
}