package com.dev.gold.localweather.model


import io.reactivex.Observable

interface WeatherDataModel {

    fun getLocations(query: String): Observable<List<Location>>

    fun getLocationInfo(woeid: Int): Observable<LocationInfo>

}