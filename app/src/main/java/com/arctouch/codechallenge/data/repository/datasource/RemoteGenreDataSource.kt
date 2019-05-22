package com.arctouch.codechallenge.data.repository.datasource

import com.arctouch.codechallenge.data.cache.GenreCache
import com.arctouch.codechallenge.data.entity.GenreEntity
import com.arctouch.codechallenge.data.exception.NoNetworkException
import com.arctouch.codechallenge.data.remote.GenreService
import com.arctouch.codechallenge.data.remote.NetworkHandler
import io.reactivex.Single

class RemoteGenreDataSource(private val genreService: GenreService,
                            private val genreCache: GenreCache,
                            private val networkHandler: NetworkHandler) : GenreDataSource {

    override fun getAll(): Single<List<GenreEntity>> {
        return when (networkHandler.isConnected) {
            true -> genreService.genres().map {response
                -> genreCache.putAll(response.genres)
                response.genres
            }
            false, null -> Single.create { emitter -> emitter.onError(NoNetworkException()) }
        }
    }
}