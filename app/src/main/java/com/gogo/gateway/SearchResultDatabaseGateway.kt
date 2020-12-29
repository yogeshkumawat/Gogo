package com.gogo.gateway

import com.gogo.entity.ListData
import com.gogo.entity.Response
import io.reactivex.Observable

interface SearchResultDatabaseGateway {
    fun load(): Observable<Response<ListData>>
}