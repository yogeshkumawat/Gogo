package com.gogo.di

import com.gogo.view.DetailFragment
import com.gogo.view.ListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector()
    internal abstract fun listFragment(): ListFragment

    @ContributesAndroidInjector()
    internal abstract fun detailFragment(): DetailFragment
}