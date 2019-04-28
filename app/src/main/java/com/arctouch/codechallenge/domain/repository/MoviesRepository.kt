package com.arctouch.codechallenge.domain.repository

import com.arctouch.codechallenge.domain.Movie
import io.reactivex.Single

interface MoviesRepository {

    fun getAllWithGenres(query: String?, page: Long): Single<List<Movie>>
}