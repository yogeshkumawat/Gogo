package com.gogo

import com.gogo.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication


class GogoApp : DaggerApplication() {

    private val androidInjector = lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        DaggerAppComponent.factory().create(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return androidInjector.value
    }
}