package com.dev.gold.localweather.viewmodel

import android.arch.lifecycle.*
import android.databinding.ObservableField
import android.util.Log
import com.dev.gold.localweather.model.WeatherListData
import com.dev.gold.localweather.repository.WeatherRepo
import com.dev.gold.localweather.repository.WeatherRepoImpl
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val weatherRepo: WeatherRepo) : ViewModel(), LifecycleObserver {

    val weatherLiveData = MutableLiveData<List<WeatherListData>>()

    val isLoading = ObservableField<Boolean>(false)

    val isFirst = ObservableField<Boolean>(true)

    private val disposable = CompositeDisposable()

    //private val weatherRepo: WeatherRepo by lazy { WeatherRepoImpl() }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun getLocations() {

        val weatherList = mutableListOf<WeatherListData>()

        weatherRepo.getLocations("se")
            .doOnSubscribe {
                if (isFirst.get()!!) isLoading.set(false) else isLoading.set(true)
                weatherLiveData.postValue(null)
            }
            .subscribeOn(Schedulers.io())
            .flatMap {
                Observable.fromIterable(it)
            }
            .concatMapEager {
                weatherRepo.getLocationInfo(it.woeid)
            }
            .doFinally {
                isFirst.set(false)
                isLoading.set(false)
            }
            .observeOn(AndroidSchedulers.mainThread())
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

    private tailrec fun makeFakeDataForHeader(
        list: List<WeatherListData>,
        acc: List<WeatherListData>
    ): List<WeatherListData> = when {
        list.isEmpty() -> acc
        acc.isEmpty() -> makeFakeDataForHeader(list, listOf(makeFakeData()))
        else -> makeFakeDataForHeader(list.drop(1), acc.plus(list.first()))
    }

    private fun makeFakeData(): WeatherListData {
        return WeatherListData(
            title = "head",
            today = WeatherListData.Today(),
            tomorrow = WeatherListData.Tomorrow()
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}