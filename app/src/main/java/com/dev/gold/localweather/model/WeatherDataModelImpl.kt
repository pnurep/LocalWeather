package com.dev.gold.localweather.model


import com.dev.gold.localweather.interfaces.WeatherApi
import io.reactivex.Observable


class WeatherDataModelImpl(private val weatherApi: WeatherApi) : WeatherDataModel {

    override fun getLocations(query: String): Observable<List<Location>> {
        return weatherApi.getLocations(query)
    }

    override fun getLocationInfo(woeid: Int): Observable<LocationInfo> {
        return weatherApi.getLocationInfo(woeid)
    }

}