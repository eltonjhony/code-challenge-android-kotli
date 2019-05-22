package com.arctouch.codechallenge.data.repository.datasource

import com.arctouch.codechallenge.data.entity.MovieEntity
import com.arctouch.codechallenge.data.exception.NoNetworkException
import com.arctouch.codechallenge.data.remote.MoviesService
import com.arctouch.codechallenge.data.remote.NetworkHandler
import io.reactivex.Single

class RemoteMoviesDataSource(private val moviesService: MoviesService,
                             private val networkHandler: NetworkHandler) : MoviesDataSource {

    override fun getAllBy(page: Long): Single<List<MovieEntity>> {
        return when (networkHandler.isConnected) {
            true -> moviesService.upcomingMovies(page)
                    .map { it.results }
            false, null -> Single.create { emitter -> emitter.onError(NoNetworkException()) }
        }
    }

    override fun searchMovies(query: String, page: Long): Single<List<MovieEntity>> {
        return when (networkHandler.isConnected) {
            true -> moviesService.search(query, page)
                    .map { it.results }
            false, null -> Single.create { emitter -> emitter.onError(NoNetworkException()) }
        }
    }
}