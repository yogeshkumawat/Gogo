package com.gogo

import com.gogo.di.AppComponent
import com.gogo.di.DaggerAppComponent
import dagger.android.support.DaggerApplication


class GogoApp() : DaggerApplication() {

    init {
        appInstance = this
    }

    private val androidInjector = lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        DaggerAppComponent.factory().create(this) as DaggerAppComponent
    }

    public override fun applicationInjector(): AppComponent {
        return androidInjector.value
    }

    companion object {
        lateinit var appInstance: GogoApp
    }

}