package com.arctouch.codechallenge.domain.interactor

import com.arctouch.codechallenge.domain.interactor.GetMoviesWithGenresUseCase.Params
import com.arctouch.codechallenge.domain.Movie
import com.arctouch.codechallenge.domain.executor.SchedulersFacade
import com.arctouch.codechallenge.domain.repository.MoviesRepository
import io.reactivex.Single
import javax.inject.Inject

class GetMoviesWithGenresUseCase
@Inject constructor(private val moviesRepository: MoviesRepository,
                    schedulersFacade: SchedulersFacade) : UseCase<List<Movie>, Params>(schedulersFacade) {

    override fun run(params: Params): Single<List<Movie>> = moviesRepository.getAllWithGenres(params.query, params.page)

    data class Params(val query: String?, val page: Long)
}

