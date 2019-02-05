package com.dev.gold.localweather.di

import com.dev.gold.localweather.di.ui.MainModule
import com.dev.gold.localweather.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBinder {

    // MainActivity 를 객체 그래프에 추가할 수 있도록 한다
    @ContributesAndroidInjector(modules = arrayOf(MainModule::class))
    abstract fun bindMainActivity(): MainActivity

}