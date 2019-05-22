package com.arctouch.codechallenge.data.repository.datasource.factory

import com.arctouch.codechallenge.data.remote.MoviesService
import com.arctouch.codechallenge.data.remote.NetworkHandler
import com.arctouch.codechallenge.data.repository.datasource.MoviesDataSource
import com.arctouch.codechallenge.data.repository.datasource.RemoteMoviesDataSource
import javax.inject.Inject

class MovieDataSourceFactory
@Inject constructor(private val moviesService: MoviesService,
                    private val networkHandler: NetworkHandler) {

    fun create(): MoviesDataSource {
        return RemoteMoviesDataSource(moviesService, networkHandler)
    }
}