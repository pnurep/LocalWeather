package com.dev.gold.localweather.model

data class WeatherListData(
    val title: String = "",
    val woeid: Int = 0,
    val today: Today,
    val tomorrow: Tomorrow
) {

    data class Today(
            val weather_state_name: String = "",
            val weather_state_abbr: String = "",
            val the_temp: Double = 0.0,
            val humidity: Int = 0
    )

    data class Tomorrow(
            val weather_state_name: String = "",
            val weather_state_abbr: String = "",
            val the_temp: Double = 0.0,
            val humidity: Int = 0
    )

}