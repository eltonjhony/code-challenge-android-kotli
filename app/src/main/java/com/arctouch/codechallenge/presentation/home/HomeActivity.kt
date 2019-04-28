package com.arctouch.codechallenge.presentation.home

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.internal.extensions.*
import com.arctouch.codechallenge.presentation.common.AbstractPaginatedSearchListActivity
import com.arctouch.codechallenge.presentation.common.Resource
import com.arctouch.codechallenge.presentation.common.ResourceState
import com.arctouch.codechallenge.presentation.details.DetailsActivity
import com.arctouch.codechallenge.presentation.model.MovieView
import kotlinx.android.synthetic.main.home_activity.*

class HomeActivity : AbstractPaginatedSearchListActivity() {

    private lateinit var moviesViewModel : MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        app.component.inject(this)

        setupUi()
        initViewModel()
    }

    override fun onLoad(query: String?, nextPage: Long) {
        moviesViewModel.load(query, nextPage)
    }

    private fun setupUi() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.initEndlessScrollListener()
        recyclerView.adapter = HomeAdapter(emptyList()) { movie ->
            DetailsActivity.callingIntent(this, movie)
        }
    }

    private fun initViewModel() {
        moviesViewModel = viewModel(viewModelFactory)
        moviesViewModel.movies.observe(this, Observer { updateMovies(it) })
    }

    private fun updateMovies(resource: Resource<MutableList<MovieView>>?) {
        resource?.let {
            when (it.state) {
                ResourceState.LOADING -> startLoading()
                ResourceState.SUCCESS -> stopLoading()
                ResourceState.ERROR -> stopLoading()
            }
            it.data?.let { movies -> renderMoviesList(movies) }
            it.throwable?.let { t -> renderFailure(t) }
        }
    }

    private fun renderMoviesList(movies: MutableList<MovieView>?) {
        (recyclerView.adapter as HomeAdapter).setItems(movies!!)
    }

    private fun stopLoading() {
        setEndlessScrollLoader(false)
        progressBar.visibility(false)
    }

    private fun startLoading() {
        setEndlessScrollLoader(true)
        progressBar.visibility(true)
    }

    private fun renderFailure(throwable: Throwable?) {
        showErrorFor(throwable)
    }
}