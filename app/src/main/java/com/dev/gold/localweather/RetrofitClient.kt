package com.dev.gold.localweather

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val baseUrl = "https://www.metaweather.com/"

    val locationsApi: WeatherApi
        get() {
            return retrofit!!.create(WeatherApi::class.java)
        }

    var retrofit: Retrofit? = null
        get() {
            field = field ?: setUpRetrofit()
            return field
        }

    private fun setUpRetrofit(): Retrofit {
        return Retrofit.Builder()
                .client(OkHttpClient.Builder()
                        .addInterceptor(HttpLoggingInterceptor
                        { Log.d("OKHTTP : ", it)}).build())
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

}