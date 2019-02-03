package com.dev.gold.localweather.model

import com.dev.gold.localweather.model.Location
import com.dev.gold.localweather.model.LocationInfo
import io.reactivex.Observable

interface WeatherDataModel {

    fun getLocations(query: String): Observable<List<Location>>

    fun getLocationInfo(woeid: Int): Observable<LocationInfo>

}