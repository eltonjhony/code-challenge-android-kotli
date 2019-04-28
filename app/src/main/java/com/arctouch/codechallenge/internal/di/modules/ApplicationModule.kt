package com.arctouch.codechallenge.internal.di.modules

import android.content.Context
import com.arctouch.codechallenge.MyApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: MyApplication) {

    @Provides
    @Singleton
    fun provideApplication(): Context = application.applicationContext
}