package com.arctouch.codechallenge.data.repository.datasource.factory

import com.arctouch.codechallenge.data.cache.GenreCache
import com.arctouch.codechallenge.data.remote.GenreService
import com.arctouch.codechallenge.data.remote.NetworkHandler
import com.arctouch.codechallenge.data.repository.datasource.GenreDataSource
import com.arctouch.codechallenge.data.repository.datasource.LocalGenreDataSource
import com.arctouch.codechallenge.data.repository.datasource.RemoteGenreDataSource
import javax.inject.Inject

class GenreDataSourceFactory
@Inject constructor(private val genreService: GenreService,
                    private val genreCache: GenreCache,
                    private val networkHandler: NetworkHandler) {

    fun create(): GenreDataSource {
        return when (genreCache.hasExpired()) {
            true -> createRemoteDataSource()
            else -> createLocalDataSource()
        }
    }

    private fun createRemoteDataSource(): GenreDataSource {
        return RemoteGenreDataSource(genreService, genreCache, networkHandler)
    }

    private fun createLocalDataSource(): GenreDataSource {
        return LocalGenreDataSource(genreCache)
    }
}