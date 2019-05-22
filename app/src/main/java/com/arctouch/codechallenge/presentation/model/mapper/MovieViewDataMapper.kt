package com.arctouch.codechallenge.presentation.model.mapper

import com.arctouch.codechallenge.domain.Movie
import com.arctouch.codechallenge.presentation.model.MovieView
import javax.inject.Inject

class MovieViewDataMapper @Inject constructor() {

    fun transform(model: Movie) : MovieView {
        return MovieView(model.id,
                model.title,
                model.overview,
                model.getGenresListAsString(),
                model.buildPosterUrl(),
                model.buildBackdropUrl(),
                model.releaseDate)
    }
}