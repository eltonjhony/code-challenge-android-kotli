package com.arctouch.codechallenge.data.remote

import com.arctouch.codechallenge.ApplicationProperties
import com.arctouch.codechallenge.data.entity.MovieEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesService {

    @GET("movie/upcoming")
    fun upcomingMovies(
        @Query("page") page: Long,
        @Query("api_key") apiKey: String = ApplicationProperties.ApiConstants.API_KEY
    ): Single<MoviesResponse>

    @GET("search/movie")
    fun search(
            @Query("query") query: String,
            @Query("page") page: Long,
            @Query("api_key") apiKey: String = ApplicationProperties.ApiConstants.API_KEY
    ): Single<MoviesResponse>

    @GET("movie/{id}")
    fun movie(
        @Path("id") id: Long,
        @Query("language") language: String,
        @Query("api_key") apiKey: String = ApplicationProperties.ApiConstants.API_KEY
    ): Single<MovieEntity>
}
