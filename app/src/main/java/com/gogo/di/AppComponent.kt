package com.gogo.di

import com.gogo.GogoApp
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@AppScope
@Component(
    modules = [AndroidSupportInjectionModule::class,
        AndroidInjectionModule::class, AppModule::class, ActivityModule::class, ViewModelFactoryModule::class, ViewModelModule::class]
)
interface AppComponent : AndroidInjector<GogoApp> {

    @Component.Factory
    interface Factory : AndroidInjector.Factory<GogoApp>

}