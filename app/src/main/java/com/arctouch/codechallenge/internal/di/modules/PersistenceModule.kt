package com.arctouch.codechallenge.internal.di.modules

import android.content.Context
import com.arctouch.codechallenge.data.cache.GenreCache
import com.arctouch.codechallenge.data.local.GenreDao
import com.arctouch.codechallenge.data.local.MoviesRoomDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PersistenceModule {

    @Provides
    @Singleton
    fun provideGenreDao(context: Context): GenreDao = MoviesRoomDatabase.getDatabase(context).genreDao()

    @Provides
    @Singleton
    fun provideGenreCache(context: Context, genreDao: GenreDao): GenreCache {
        return GenreCache(
                genreDao,
                context.getSharedPreferences("com.arctouch.codechallenge.caching",
                        Context.MODE_PRIVATE))
    }
}