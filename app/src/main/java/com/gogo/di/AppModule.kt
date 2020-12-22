package com.gogo.di

import com.gogo.repo.Repository
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun repository(): Repository = Repository()
}