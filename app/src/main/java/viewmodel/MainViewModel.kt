package viewmodel

import androidx.lifecycle.ViewModel
import com.gogo.repo.Repository
import entity.ListData
import entity.RowItem
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

class MainViewModel() : ViewModel() {
    private val repository = Repository

    private val itemClickPublisher = BehaviorSubject.create<RowItem>()

    fun observeItemClick(): Observable<RowItem> = itemClickPublisher

    fun getList():Observable<ListData>{
        return repository.loadData();
    }

    fun itemClicked(rowItem: RowItem) {
        itemClickPublisher.onNext(rowItem)
    }
}