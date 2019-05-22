package com.arctouch.codechallenge.data.repository.datasource

import com.arctouch.codechallenge.data.entity.GenreEntity
import io.reactivex.Single

interface GenreDataSource {

    fun getAll() : Single<List<GenreEntity>>
}