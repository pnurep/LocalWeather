package com.dev.gold.localweather

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware

class LocalWeatherApp: Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        /* bindings */

    }

    
}