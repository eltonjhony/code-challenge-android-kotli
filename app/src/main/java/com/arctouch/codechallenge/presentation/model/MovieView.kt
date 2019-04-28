package com.arctouch.codechallenge.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieView(
        val id: Int,
        val title: String,
        val overview: String?,
        val genres: String?,
        val posterPath: String?,
        val backdropPath: String?,
        val releaseDate: String?
) : Parcelable

