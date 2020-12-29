package com.gogo.network

import com.gogo.entity.ListData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

//https://api.github.com/search/users?q=vipul&per_page=20&page=1
interface NetworkClient {
//    @GET("/search/users?q={query}&per_page=20&page={page}")
    @GET("/search/users?q=vipul&per_page=20&page=1")
    fun fetchResult(
//        @Path("query") query: String,
//        @Path("page") page: String
    ): Call<ListData?>
}