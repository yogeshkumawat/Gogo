package com.gogo.view

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.gogo.R
import com.gogo.viewmodel.MainViewModel
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity()/*, HasAndroidInjector*/ {
//    private val viewModel: MainViewModel by viewModels()

    /*@Inject
    lateinit var injector: DispatchingAndroidInjector<Any>*/

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
//        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        AndroidInjection.inject(this)
//        Log.v("BOSS2","MainActivity: Repository= $repository View Model= $viewModel")
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        start_button.setOnClickListener { startActivity(Intent(this, MainActivity2::class.java)) }

    }

    /*override fun androidInjector(): AndroidInjector<Any> {
        return injector
    }*/
}