package com.arctouch.codechallenge.data.repository.datasource

import com.arctouch.codechallenge.data.entity.MovieEntity
import io.reactivex.Single

interface MoviesDataSource {

    fun getAllBy(page: Long) : Single<List<MovieEntity>>

    fun searchMovies(query: String, page: Long) : Single<List<MovieEntity>>
}