package com.arctouch.codechallenge.data.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.arctouch.codechallenge.data.entity.GenreEntity

@Dao
interface GenreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(genre: GenreEntity)

    @Query("SELECT * FROM genre" )
    fun getAllGenres() : List<GenreEntity>
}