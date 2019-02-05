package com.dev.gold.localweather.di.ui


import com.dev.gold.localweather.ListAdapter
import com.dev.gold.localweather.repository.WeatherRepo
import com.dev.gold.localweather.viewmodel.MainViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    fun provideViewModelFactory(weatherRepo: WeatherRepo): MainViewModelFactory {
        return MainViewModelFactory(weatherRepo)
    }

    @Provides
    fun provideListAdapter(): ListAdapter {
        return ListAdapter()
    }

}