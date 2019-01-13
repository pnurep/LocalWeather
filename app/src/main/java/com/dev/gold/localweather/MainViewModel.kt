package com.dev.gold.localweather

import android.arch.lifecycle.*
import android.databinding.ObservableField
import android.util.Log
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class MainViewModel : ViewModel(), LifecycleObserver {

    val weatherLiveData = MutableLiveData<List<WeatherListData>>()

    val isLoading = ObservableField<Boolean>(false)

    val isFirst = ObservableField<Boolean>(true)

    private val disposable = CompositeDisposable()


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun getLocations() {

        val weatherList = mutableListOf<WeatherListData>()

        RetrofitClient.locationsApi.getLocations("se")
                .doOnSubscribe {
                    if (isFirst.get()!!) isLoading.set(false) else isLoading.set(true)
                    weatherLiveData.postValue(null)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap {
                    Observable.fromIterable(it)
                }
                .concatMapEager {
                    RetrofitClient.locationsApi.getLocationInfo(it.woeid)
                }
                .doFinally {
                    isFirst.set(false)
                    isLoading.set(false)
                }
                .subscribeBy(
                        onNext = {
                            val temp0 = it.consolidatedWeather[0]
                            val temp1 = it.consolidatedWeather[1]

                            weatherList.add(
                                    WeatherListData(
                                            it.title,
                                            it.woeid,
                                            WeatherListData.Today(
                                                    temp0.weatherStateName,
                                                    temp0.weatherStateAbbr,
                                                    temp0.theTemp,
                                                    temp0.humidity
                                            ),
                                            WeatherListData.Tomorrow(
                                                    temp1.weatherStateName,
                                                    temp1.weatherStateAbbr,
                                                    temp1.theTemp,
                                                    temp1.humidity
                                            )
                                    )
                            )
                        },
                        onError = {
                            Log.e("API CALL ERROR : ", it.message)
                        },
                        onComplete = {
                            weatherLiveData.postValue(makeFakeDataForHeader(weatherList, listOf()))
                        }
                ).addTo(disposable)
    }

    private tailrec fun makeFakeDataForHeader(list: List<WeatherListData>, acc: List<WeatherListData>): List<WeatherListData> = when {
        list.isEmpty() -> acc
        acc.isEmpty() -> makeFakeDataForHeader(list, listOf(makeFakeData()))
        else -> makeFakeDataForHeader(list.drop(1), acc.plus(list.first()))
    }

    private fun makeFakeData(): WeatherListData {
        return WeatherListData("head", 0,
                WeatherListData.Today("", "", 0.toDouble(), 0),
                WeatherListData.Tomorrow("", "", 0.toDouble(), 0))
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}