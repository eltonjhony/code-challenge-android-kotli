package com.arctouch.codechallenge.presentation.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.presentation.common.BaseActivity
import com.arctouch.codechallenge.internal.extensions.displayHomeAsUpEnabled
import com.arctouch.codechallenge.internal.extensions.loadFromUrl
import com.arctouch.codechallenge.presentation.model.MovieView
import kotlinx.android.synthetic.main.details_activity.*

class DetailsActivity : BaseActivity() {

    companion object {
        const val MOVIE_EXTRA_KEY = "MOVIE_EXTRA_PARAM"

        fun callingIntent(context: Context, movie: MovieView) {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(DetailsActivity.MOVIE_EXTRA_KEY, movie)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_activity)

        setupUi()
    }

    private fun setupUi() {
        val movie = intent.getParcelableExtra<MovieView>(MOVIE_EXTRA_KEY)

        parentCollapsingToolbar.title = movie.title
        releaseDateTextView.text = movie.releaseDate
        overviewTextView.text = movie.overview
        genresTextView.text = movie.genres
        movie.backdropPath?.let { backdropImageView.loadFromUrl(it) }
        movie.posterPath?.let { posterImageView.loadFromUrl(it) }

        displayHomeAsUpEnabled()
    }
}