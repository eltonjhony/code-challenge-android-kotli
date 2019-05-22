package com.arctouch.codechallenge.data.entity

import com.google.gson.annotations.SerializedName

data class MovieEntity(
        val id: Int,
        val title: String,
        val overview: String?,
        val genres: List<GenreEntity>,
        @SerializedName("genre_ids") val genreIds: List<Int>?,
        @SerializedName("poster_path") val posterPath: String?,
        @SerializedName("backdrop_path") val backdropPath: String?,
        @SerializedName("release_date") val releaseDate: String?
)