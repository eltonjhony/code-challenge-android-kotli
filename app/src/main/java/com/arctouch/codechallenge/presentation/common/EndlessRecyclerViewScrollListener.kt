package com.arctouch.codechallenge.presentation.common

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

abstract class EndlessRecyclerViewScrollListener(private val linearLayoutManager: LinearLayoutManager) :
        RecyclerView.OnScrollListener() {

    companion object {
        private const val NUMBER_OF_REMAINING_ITEMS: Int = 1
    }

    var currentPage = 1L
    private set

    var isLoading: Boolean = false
    
    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition()
        val totalItemCount = linearLayoutManager.itemCount

        if (!isLoading && lastVisibleItemPosition == (totalItemCount - NUMBER_OF_REMAINING_ITEMS)) {
            currentPage++
            onLoadMore(currentPage)
        }
    }

    fun resetPagination() {
        currentPage = 1
        isLoading = false
    }

    abstract fun onLoadMore(nextPage: Long)
}