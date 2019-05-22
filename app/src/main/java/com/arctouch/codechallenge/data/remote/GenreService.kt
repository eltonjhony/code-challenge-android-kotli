package com.arctouch.codechallenge.data.remote

import com.arctouch.codechallenge.ApplicationProperties
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GenreService {

    @GET("genre/movie/list")
    fun genres(
        @Query("api_key") apiKey: String = ApplicationProperties.ApiConstants.API_KEY
    ): Single<GenreResponse>
}
