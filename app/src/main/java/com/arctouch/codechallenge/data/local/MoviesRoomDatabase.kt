package com.arctouch.codechallenge.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

import com.arctouch.codechallenge.data.entity.GenreEntity

@Database(entities = [GenreEntity::class], version = 1, exportSchema = false)
abstract class MoviesRoomDatabase : RoomDatabase() {

    abstract fun genreDao() : GenreDao

    companion object {

        fun getDatabase(context: Context): MoviesRoomDatabase {
            return Room.databaseBuilder(context.applicationContext,
                MoviesRoomDatabase::class.java, "movies.db")
                .build()
        }
    }
}