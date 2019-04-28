package com.arctouch.codechallenge.domain

import com.arctouch.codechallenge.ApplicationProperties

data class Movie(
        val id: Int,
        val title: String,
        val overview: String?,
        val genres: List<Genre>?,
        val genreIds: List<Int>?,
        val posterPath: String?,
        val backdropPath: String?,
        val releaseDate: String?
) {

    fun getGenresListAsString(): String? {
        return genres?.joinToString(separator = ", ") { it.name }
    }

    fun buildPosterUrl(): String {
        return ApplicationProperties.ApiConstants.POSTER_URL + posterPath + "?api_key=" + ApplicationProperties.ApiConstants.API_KEY
    }

    fun buildBackdropUrl(): String {
        return ApplicationProperties.ApiConstants.BACKDROP_URL + backdropPath + "?api_key=" + ApplicationProperties.ApiConstants.API_KEY
    }
}