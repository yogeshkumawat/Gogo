package com.gogo.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.gogo.R
import com.gogo.repo.Repository
import com.gogo.viewmodel.MainViewModel
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity2 : AppCompatActivity()/*, HasAndroidInjector */{
    /*private val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var injector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var repository: Repository*/

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)

//        viewModel.getList()
//        Log.v("BOSS2","MainActivity22222: Repository= $repository View Model= $viewModel")
        start_button.setOnClickListener { startActivity(Intent(this, MainActivity::class.java)) }

    }

    /*override fun androidInjector(): AndroidInjector<Any> {
        return injector
    }*/
}