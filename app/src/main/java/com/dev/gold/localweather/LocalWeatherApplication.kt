package com.dev.gold.localweather

import android.app.Application
import android.content.Context
import com.dev.gold.localweather.model.WeatherDataModel
import com.dev.gold.localweather.model.WeatherDataModelImpl
import com.dev.gold.localweather.repository.WeatherRepo
import com.dev.gold.localweather.repository.WeatherRepoImpl
import com.dev.gold.localweather.view.MainActivity
import com.dev.gold.localweather.viewmodel.MainViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.ActivityRetainedScope
import org.kodein.di.bindings.WeakContextScope
import org.kodein.di.generic.*

class LocalWeatherApplication: Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        /* bindings */
        bind<WeatherDataModel>() with singleton { WeatherDataModelImpl() }
        bind<WeatherRepo>() with singleton { WeatherRepoImpl(instance()) }
        bind() from provider { MainViewModelFactory(instance()) }

        // 액티비티 스콥에 바인딩 되어 있으므로 동일한 액티비티를 사용하는 retrieval 은 동일한 ListAdapter 인스턴스를 반환한다.
        bind<ListAdapter>() with scoped(WeakContextScope.of<MainActivity>()).singleton { ListAdapter() }
    }
    
}