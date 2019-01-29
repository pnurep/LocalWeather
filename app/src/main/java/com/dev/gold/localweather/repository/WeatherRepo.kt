package com.dev.gold.localweather.repository

import com.dev.gold.localweather.interfaces.WeatherContract
import com.dev.gold.localweather.model.Location
import com.dev.gold.localweather.model.LocationInfo
import com.dev.gold.localweather.model.WeatherDataModel
import io.reactivex.Observable


class WeatherRepo {

    private val weatherDataModel: WeatherContract by lazy { WeatherDataModel() }

    fun getLocations(query: String): Observable<List<Location>> {
        return weatherDataModel.getLocations(query)
    }

    fun getLocationInfo(woeid: Int): Observable<LocationInfo> {
        return weatherDataModel.getLocationInfo(woeid)
    }

}