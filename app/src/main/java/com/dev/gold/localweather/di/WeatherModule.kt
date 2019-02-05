package com.dev.gold.localweather.di

import com.dev.gold.localweather.interfaces.WeatherApi
import com.dev.gold.localweather.model.WeatherDataModel
import com.dev.gold.localweather.model.WeatherDataModelImpl
import com.dev.gold.localweather.repository.WeatherRepo
import com.dev.gold.localweather.repository.WeatherRepoImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class WeatherModule {

    @Provides
    @Singleton
    fun provideWeatherRepo(weatherDataModel: WeatherDataModel): WeatherRepo {
        return WeatherRepoImpl(weatherDataModel)
    }

    @Provides
    @Singleton
    fun provideWeatherDataModel(weatherApi: WeatherApi): WeatherDataModel {
        return WeatherDataModelImpl(weatherApi)
    }

}