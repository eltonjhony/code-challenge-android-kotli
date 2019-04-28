package com.arctouch.codechallenge.presentation.home

import android.arch.lifecycle.MutableLiveData
import com.arctouch.codechallenge.domain.Movie
import com.arctouch.codechallenge.domain.interactor.GetMoviesWithGenresUseCase
import com.arctouch.codechallenge.domain.interactor.GetMoviesWithGenresUseCase.Params
import com.arctouch.codechallenge.internal.extensions.setFailure
import com.arctouch.codechallenge.internal.extensions.setLoading
import com.arctouch.codechallenge.internal.extensions.setSuccess
import com.arctouch.codechallenge.presentation.common.AbstractPaginatedSearchViewModel
import com.arctouch.codechallenge.presentation.common.Resource
import com.arctouch.codechallenge.presentation.model.MovieView
import com.arctouch.codechallenge.presentation.model.mapper.MovieViewDataMapper
import javax.inject.Inject

class MoviesViewModel
@Inject constructor(private val getMoviesWithGenres: GetMoviesWithGenresUseCase,
                    private val movieViewDataMapper: MovieViewDataMapper) : AbstractPaginatedSearchViewModel() {

    val movies = MutableLiveData<Resource<MutableList<MovieView>>>()

    init { load() }

    override fun load(query: String?, page: Long) {
        movies.setLoading()
        getMoviesWithGenres(Params(query, page)) { result: List<Movie>?, throwable: Throwable? ->
            handleMovieList(result, page)
            handleFailure(throwable)
        }
    }

    private fun handleMovieList(result: List<Movie>?, page: Long) {
        result?.let {
            movies.setSuccess(movies.value!!.append(it.map {
                movieViewDataMapper.transform(it)
            }, page))
        }
    }

    private fun handleFailure(throwable: Throwable?) {
        throwable?.let { movies.setFailure(it) }
    }

    override fun onCleared() {
        getMoviesWithGenres.dispose()
        super.onCleared()
    }
}
