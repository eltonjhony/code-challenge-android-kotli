package com.arctouch.codechallenge

import android.app.Application
import com.arctouch.codechallenge.internal.di.components.ApplicationComponent
import com.arctouch.codechallenge.internal.di.components.DaggerApplicationComponent
import com.arctouch.codechallenge.internal.di.modules.ApplicationModule

class MyApplication : Application() {

    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }
}