package com.dev.gold.localweather.repository

import com.dev.gold.localweather.model.Location
import com.dev.gold.localweather.model.LocationInfo
import com.dev.gold.localweather.model.WeatherDataModel
import io.reactivex.Observable


class WeatherRepoImpl(private val weatherDataModel: WeatherDataModel): WeatherRepo {

    override fun getLocations(query: String): Observable<List<Location>> {
        return weatherDataModel.getLocations(query)
    }

    override fun getLocationInfo(woeid: Int): Observable<LocationInfo> {
        return weatherDataModel.getLocationInfo(woeid)
    }

}