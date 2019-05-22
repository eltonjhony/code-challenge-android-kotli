package com.arctouch.codechallenge.internal.di.modules

import com.arctouch.codechallenge.data.entity.mapper.MovieEntityDataMapper
import com.arctouch.codechallenge.data.repository.MoviesDataRepository
import com.arctouch.codechallenge.domain.repository.MoviesRepository
import com.arctouch.codechallenge.data.repository.datasource.factory.GenreDataSourceFactory
import com.arctouch.codechallenge.data.repository.datasource.factory.MovieDataSourceFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideMoviesDataRepository(movieDataSourceFactory: MovieDataSourceFactory,
                                    genreDataSourceFactory: GenreDataSourceFactory,
                                    movieEntityDataMapper: MovieEntityDataMapper) : MoviesRepository {
        return MoviesDataRepository(movieDataSourceFactory, genreDataSourceFactory, movieEntityDataMapper)
    }
}