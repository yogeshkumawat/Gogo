package com.gogo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.gogo.entity.RowItem
import com.gogo.repo.ListDataSource
import com.gogo.repo.SearchResultRepository

class ListDataSourceFactory(
    private val searchResultRepository: SearchResultRepository
)
    : DataSource.Factory<Int, RowItem>() {

    val newsDataSourceLiveData = MutableLiveData<ListDataSource>()

    override fun create(): DataSource<Int, RowItem> {
        val newsDataSource = ListDataSource(searchResultRepository)
        newsDataSourceLiveData.postValue(newsDataSource)
        return newsDataSource
    }
}