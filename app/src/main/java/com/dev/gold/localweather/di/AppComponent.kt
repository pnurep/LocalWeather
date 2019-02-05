package com.dev.gold.localweather.di

import android.app.Application
import com.dev.gold.localweather.LocalWeatherApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = arrayOf(
        AppModule::class,
        RetrofitModule::class,
        OkHttpModule::class,
        WeatherModule::class,
        AndroidSupportInjectionModule::class,
        ActivityBinder::class
    )
)
interface AppComponent: AndroidInjector<LocalWeatherApplication> {

    @Component.Builder
    interface Builder {

        // @BindInstance 어노테이션으로 객체 그래프에 추가할 객체를 선언
        // 객체 그래프에 추가할 객체를 인자로 받고, 빌더 클래스를 반환하는 함수 형태로 선언한다.
        @BindsInstance
        fun application(app: Application): Builder

        // 빌더 클래스는 컴포넌트를 반환하는 build() 함수를 반드시 포함해야 한다.
        fun build(): AppComponent

    }

}