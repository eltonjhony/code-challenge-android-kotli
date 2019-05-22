package com.arctouch.codechallenge.data.repository

import com.arctouch.codechallenge.data.entity.GenreEntity
import com.arctouch.codechallenge.data.entity.MovieEntity
import com.arctouch.codechallenge.data.entity.mapper.MovieEntityDataMapper
import com.arctouch.codechallenge.data.repository.datasource.factory.GenreDataSourceFactory
import com.arctouch.codechallenge.data.repository.datasource.factory.MovieDataSourceFactory
import com.arctouch.codechallenge.domain.Movie
import com.arctouch.codechallenge.domain.repository.MoviesRepository
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class MoviesDataRepository
@Inject constructor(private val movieDataSourceFactory: MovieDataSourceFactory,
                    private val genreDataSourceFactory: GenreDataSourceFactory,
                    private val movieEntityDataMapper: MovieEntityDataMapper) : MoviesRepository {

    override fun getAllWithGenres(query: String?, page: Long): Single<List<Movie>> {

        val moviesDataSource = movieDataSourceFactory.create()
        val genreDataSource = genreDataSourceFactory.create()

        val moviesObservable = when {
            query != null && query.isNotEmpty() -> moviesDataSource.searchMovies(query, page)
            else -> moviesDataSource.getAllBy(page)
        }

        return extractMoviesWithGenres(moviesObservable, genreDataSource.getAll())
                .map { movieEntityDataMapper.transform(it) }
    }

    private fun extractMoviesWithGenres(moviesEntity: Single<List<MovieEntity>>, genresEntity: Single<List<GenreEntity>>): Single<List<MovieEntity>> {
        return Single.zip(moviesEntity, genresEntity,
                BiFunction<List<MovieEntity>, List<GenreEntity>, List<MovieEntity>> { movies: List<MovieEntity>, genres: List<GenreEntity> ->
                    movies.map { movie ->
                        movie.copy(genres = genres.filter { movie.genreIds?.contains(it.id) == true })
                    }.filter { movieEntity -> movieEntity.genres.isNotEmpty() }
                })
    }
}