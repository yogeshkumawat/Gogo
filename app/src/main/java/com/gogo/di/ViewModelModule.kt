package com.gogo.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gogo.viewmodel.MainViewModel
import com.gogo.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMyViewModel(myViewModel: MainViewModel): ViewModel
}