package com.dev.gold.localweather.di

import com.dev.gold.localweather.interfaces.WeatherApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RetrofitModule {

    companion object {

        private const val baseUrl = "https://www.metaweather.com/"

    }

    // WeatherApi 객체를 제공
    // 이 객체를 생성할 때 필요한 객체들은 함수의 인자로 선언
    @Provides
    @Singleton
    fun provideWeatherApi(
        okHttpClient: OkHttpClient,
        callAdapter: CallAdapter.Factory,
        converter: Converter.Factory
    ): WeatherApi {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addCallAdapterFactory(callAdapter)
            .addConverterFactory(converter)
            .build()
            .create(WeatherApi::class.java)
    }

    @Provides
    fun provideCallAdapterFactory(): CallAdapter.Factory
            = RxJava2CallAdapterFactory.createAsync()

    @Provides
    fun provideConverterFactory(): Converter.Factory
            = GsonConverterFactory.create()

}