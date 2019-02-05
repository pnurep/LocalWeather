package com.dev.gold.localweather.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.dev.gold.localweather.repository.WeatherRepo

class MainViewModelFactory(private val weatherRepo: WeatherRepo): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(weatherRepo) as T
    }

}