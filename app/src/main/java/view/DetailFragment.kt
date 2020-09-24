package view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.gogo.R
import com.gogo.databinding.LayoutFragmentDetailBinding
import entity.RowItem
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import viewmodel.MainViewModel

class DetailFragment : Fragment() {
    private val viewModel: MainViewModel by activityViewModels()
    lateinit var binding: LayoutFragmentDetailBinding
    private val disposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.layout_fragment_detail, container, false
            )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeItemClick()
    }

    private fun observeItemClick() {
        viewModel.observeItemClick()
            .subscribe {
                itemClicked(it)
            }
            .disposeBy(disposable)
    }

    private fun itemClicked(rowItem: RowItem) {
        binding.item = rowItem
    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }

    private fun Disposable.disposeBy(compositeDisposable: CompositeDisposable) {
        compositeDisposable.add(this)
    }
}