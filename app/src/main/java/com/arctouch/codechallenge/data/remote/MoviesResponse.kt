package com.arctouch.codechallenge.data.remote

import com.arctouch.codechallenge.data.entity.MovieEntity
import com.google.gson.annotations.SerializedName

data class MoviesResponse(
        val page: Int,
        var results: List<MovieEntity>,
        @SerializedName("total_pages") val totalPages: Int,
        @SerializedName("total_results") var totalResults: Int)