package com.dev.gold.localweather

data class WeatherListData(
    val title: String,
    val woeid: Int,
    val today: Today,
    val tomorrow: Tomorrow
) {

    data class Today(
            val weather_state_name: String,
            val weather_state_abbr: String,
            val the_temp: Double,
            val humidity: Int
    )

    data class Tomorrow(
            val weather_state_name: String,
            val weather_state_abbr: String,
            val the_temp: Double,
            val humidity: Int
    )

}