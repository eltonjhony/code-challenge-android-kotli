package com.arctouch.codechallenge.internal.di.components

import com.arctouch.codechallenge.MyApplication
import com.arctouch.codechallenge.internal.di.modules.*
import com.arctouch.codechallenge.internal.di.modules.ViewModelModule
import com.arctouch.codechallenge.presentation.home.HomeActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class,
    PersistenceModule::class, ViewModelModule::class, RepositoryModule::class])
interface ApplicationComponent {

    fun inject(application: MyApplication)
    fun inject(homeActivity: HomeActivity)
}