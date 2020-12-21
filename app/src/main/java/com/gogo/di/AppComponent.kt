package com.gogo.di

import com.gogo.GogoApp
import com.gogo.view.MyAdapter
import com.gogo.viewmodel.MainViewModel
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@AppScope
@Component(
    modules = [AndroidSupportInjectionModule::class,
        AndroidInjectionModule::class, AppModule::class, ActivityModule::class]
)
interface AppComponent : AndroidInjector<GogoApp> {
    fun inject(mainViewModel: MainViewModel)
    fun inject(myAdapter: MyAdapter)

    @Component.Factory
    interface Factory : AndroidInjector.Factory<GogoApp>

}