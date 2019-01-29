package com.dev.gold.localweather.model

import com.dev.gold.localweather.RetrofitClient
import com.dev.gold.localweather.interfaces.WeatherContract
import io.reactivex.Observable


class WeatherDataModel : WeatherContract {

    override fun getLocations(query: String): Observable<List<Location>> {
        return RetrofitClient.locationsApi.getLocations(query)
    }

    override fun getLocationInfo(woeid: Int): Observable<LocationInfo> {
        return RetrofitClient.locationsApi.getLocationInfo(woeid)
    }

}