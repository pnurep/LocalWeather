package com.dev.gold.localweather.di

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Named("applicationContext")
    @Singleton
    fun provideApplicationContext(application: Application) = application.applicationContext

}