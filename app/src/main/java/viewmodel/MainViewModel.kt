package viewmodel

import androidx.lifecycle.ViewModel
import entity.ListData
import entity.RowItem
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class MainViewModel() : ViewModel() {
    private val listUpdatePublisher = BehaviorSubject.create<List<RowItem>>()

    fun observeList(): Observable<List<RowItem>> {
        return listUpdatePublisher
    }

    fun getList():Observable<ListData>{
        return listUpdatePublisher;
    }

    private val rowItemPublisher = BehaviorSubject.create<RowItem>()

    fun observeRowItem(): Observable<RowItem> {
        return rowItemPublisher
    }
}