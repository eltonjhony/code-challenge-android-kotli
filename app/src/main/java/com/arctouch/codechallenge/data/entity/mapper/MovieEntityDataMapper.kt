package com.arctouch.codechallenge.data.entity.mapper

import com.arctouch.codechallenge.data.entity.MovieEntity
import com.arctouch.codechallenge.domain.Genre
import com.arctouch.codechallenge.domain.Movie
import javax.inject.Inject

class MovieEntityDataMapper @Inject constructor() {

    fun transform(entities: List<MovieEntity>?): List<Movie>? {
        return entities?.map { transform(it) }
    }

    private fun transform(entity: MovieEntity) : Movie {
        return Movie(entity.id,
                entity.title,
                entity.overview,
                entity.genres.map { Genre(it.id, it.name) },
                entity.genreIds,
                entity.posterPath,
                entity.backdropPath,
                entity.releaseDate)
    }
}