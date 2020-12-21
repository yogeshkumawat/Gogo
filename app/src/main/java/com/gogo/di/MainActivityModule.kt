package com.gogo.di

import android.app.Activity
import com.gogo.entity.MainActivityScope
import com.gogo.repo.Repository
import com.gogo.view.MainActivity
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    @MainActivityScope
    fun yogesh(): Repository {
        return Repository()
    }
}