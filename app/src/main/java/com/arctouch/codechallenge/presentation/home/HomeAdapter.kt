package com.arctouch.codechallenge.presentation.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.internal.extensions.loadFromUrl
import com.arctouch.codechallenge.presentation.model.MovieView
import kotlinx.android.synthetic.main.movie_item.view.*

class HomeAdapter(private var movies: List<MovieView>, val clickListener: (MovieView) -> Unit) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(movie: MovieView) {
            itemView.titleTextView.text = movie.title
            itemView.genresTextView.text = movie.genres
            itemView.releaseDateTextView.text = movie.releaseDate
            movie.posterPath?.let { itemView.posterImageView.loadFromUrl(it) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener { clickListener(movie) }
    }

    fun setItems(results: List<MovieView>) {
        movies = results
        notifyDataSetChanged()
    }
}
