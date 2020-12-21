package com.gogo.di

import com.gogo.entity.MainActivityScope
import com.gogo.view.MainActivity
import com.gogo.view.MainActivity2
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @MainActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    internal abstract fun mainActivity(): MainActivity

    @MainActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    internal abstract fun mainActivity2(): MainActivity2
}