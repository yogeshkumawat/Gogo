package com.gogo.gateway

import com.gogo.entity.ListData
import com.gogo.entity.Response
import io.reactivex.Observable

interface SearchResultNetworkGateway {
    fun load(query: String): Observable<Response<ListData>>
}