package com.gogo.repo

import androidx.paging.PageKeyedDataSource
import com.gogo.di.AppScope
import com.gogo.entity.RowItem
import com.gogo.entity.State
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

@AppScope
class ListDataSource @Inject constructor(private val repository: SearchResultRepository) : PageKeyedDataSource<Int, RowItem>() {

    private val statePublisher = BehaviorSubject.create<State>()

    fun observeState(): Observable<State> = statePublisher

    fun setState(state: State) {
        statePublisher.onNext(state)
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, RowItem>
    ) {
        setState(State.LOADING)
        repository.loadData()
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, RowItem>) {
        TODO("Not yet implemented")
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, RowItem>) {
        TODO("Not yet implemented")
    }

}