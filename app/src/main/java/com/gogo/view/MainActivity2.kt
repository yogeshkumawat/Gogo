package com.gogo.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.gogo.R
import com.gogo.viewmodel.MainViewModel
import com.gogo.viewmodel.ViewModelFactory
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity2 : DaggerAppCompatActivity() {
    private val viewModel: MainViewModel by viewModels() { viewModelFactory }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)

        Log.v(
            "BOSS2",
            "MainActivity22222: Repository= ${viewModel.repository} View Model= $viewModel"
        )
        start_button.setOnClickListener { startActivity(Intent(this, MainActivity::class.java)) }

    }
}