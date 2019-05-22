package com.arctouch.codechallenge.data.repository.datasource

import com.arctouch.codechallenge.data.cache.GenreCache
import com.arctouch.codechallenge.data.entity.GenreEntity
import io.reactivex.Single

class LocalGenreDataSource(private val genreCache: GenreCache) : GenreDataSource {

    override fun getAll(): Single<List<GenreEntity>> {
        return genreCache.getAll()
    }
}