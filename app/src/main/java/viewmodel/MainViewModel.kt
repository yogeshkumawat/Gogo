package viewmodel

import androidx.lifecycle.ViewModel
import com.gogo.repo.Repository
import entity.ListData
import entity.RowItem
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class MainViewModel() : ViewModel() {
    private val repository = Repository

    fun getList():Observable<ListData>{
        return repository.loadData();
    }
}