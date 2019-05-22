package com.arctouch.codechallenge.internal.di.modules

import com.arctouch.codechallenge.ApplicationProperties
import com.arctouch.codechallenge.data.remote.GenreService
import com.arctouch.codechallenge.data.remote.MoviesService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideApi(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(ApplicationProperties.ApiConstants.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    fun provideMoviesService(api: Retrofit): MoviesService = api.create(MoviesService::class.java)

    @Provides
    @Singleton
    fun provideGenreService(api: Retrofit): GenreService = api.create(GenreService::class.java)
}