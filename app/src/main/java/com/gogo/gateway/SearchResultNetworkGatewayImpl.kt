package com.gogo.gateway

import com.gogo.entity.ListData
import com.gogo.entity.Response
import com.gogo.network.GitRepoRestClient
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback
import javax.inject.Inject

class SearchResultNetworkGatewayImpl @Inject constructor(
    val gitRepoRestClient: GitRepoRestClient
) : SearchResultNetworkGateway {

    override fun load(): Observable<Response<ListData>> {
        return Observable.create { emitter ->

            // Fetch a list of the Github repositories.
            gitRepoRestClient.client
//                .fetchResult("vipul", "1")
                .fetchResult()
                .enqueue(object :
                    Callback<ListData?> {
                    override fun onResponse(
                        call: Call<ListData?>,
                        response: retrofit2.Response<ListData?>
                    ) {
                        if (response.isSuccessful && response.body() != null)
                            emitter.onNext(Response.Success(response.body()!!))
                        else
                            emitter.onNext(Response.Failure(Exception(response.message())))

                    }

                    override fun onFailure(call: Call<ListData?>, t: Throwable) {
                        emitter.onNext(Response.Failure(Exception(t)))
                    }
                })
        }
    }
}