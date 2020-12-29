package com.gogo.network

import com.gogo.di.AppScope
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@AppScope
class GitRepoRestClient @Inject constructor() {
    private val API_BASE_URL = "https://api.github.com"

    private val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()

    private val builder: Retrofit.Builder = Retrofit.Builder()
        .baseUrl(API_BASE_URL)
        .addConverterFactory(
            GsonConverterFactory.create()
        )

    private val retrofit: Retrofit = builder
        .client(
            httpClient.build()
        )
        .build()

    val client: NetworkClient = retrofit.create(NetworkClient::class.java)
}