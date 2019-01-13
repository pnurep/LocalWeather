package com.dev.gold.localweather

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApi {

    @GET("/api/location/search/")
    fun getLocations(@Query("query") query: String): Observable<List<Location>>

    @GET("/api/location/{woeid}")
    fun getLocationInfo(@Path("woeid") woeid: Int): Observable<LocationInfo>

}