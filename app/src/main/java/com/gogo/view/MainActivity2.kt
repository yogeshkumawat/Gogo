package com.gogo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.gogo.R
import com.gogo.viewmodel.MainViewModel
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainActivity2 : AppCompatActivity(), HasAndroidInjector {
    private val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var injector: DispatchingAndroidInjector<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)

        viewModel.getList()

    }

    override fun androidInjector(): AndroidInjector<Any> {
        return injector
    }
}