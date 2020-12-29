package com.gogo.di

import com.gogo.gateway.SearchResultDatabaseGateway
import com.gogo.gateway.SearchResultDatabaseGatewayImpl
import com.gogo.gateway.SearchResultNetworkGateway
import com.gogo.gateway.SearchResultNetworkGatewayImpl
import com.gogo.repo.SearchResultRepository
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    fun repository(searchResultRepository: SearchResultRepository) = searchResultRepository

    @Provides
    fun networkGateway(searchResultNetworkGatewayImpl: SearchResultNetworkGatewayImpl):
            SearchResultNetworkGateway = searchResultNetworkGatewayImpl

    @Provides
    fun databaseGateway(searchResultDatabaseGatewayImpl: SearchResultDatabaseGatewayImpl):
            SearchResultDatabaseGateway = searchResultDatabaseGatewayImpl
}