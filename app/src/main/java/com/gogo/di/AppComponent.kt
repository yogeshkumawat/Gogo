package com.gogo.di

import com.gogo.GogoApp
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(
    modules = [AndroidSupportInjectionModule::class,
        AndroidInjectionModule::class]
)
interface AppComponent : AndroidInjector<GogoApp> {
    @Component.Factory
    interface Factory : AndroidInjector.Factory<GogoApp>

}