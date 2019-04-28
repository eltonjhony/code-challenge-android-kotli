package com.arctouch.codechallenge.presentation.common

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import com.arctouch.codechallenge.R

abstract class AbstractPaginatedSearchListActivity : BaseActivity() {

    private var currentSearchText: String? = null

    private lateinit var scrollListener: EndlessRecyclerViewScrollListener

    fun RecyclerView.initEndlessScrollListener() {
        scrollListener = object : EndlessRecyclerViewScrollListener(layoutManager as LinearLayoutManager) {
            override fun onLoadMore(nextPage: Long) {
                onLoad(currentSearchText, nextPage)
            }
        }
        addOnScrollListener(scrollListener)
    }

    fun setEndlessScrollLoader(active: Boolean) {
        scrollListener.isLoading = active
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        val item: MenuItem = menu!!.findItem(R.id.search)
        setupSearcher(item)

        return super.onCreateOptionsMenu(menu)
    }

    private fun setupSearcher(item: MenuItem) {
        val searchView = item.actionView as SearchView
        if (!TextUtils.isEmpty(currentSearchText)) {
            item.expandActionView()
            searchView.setQuery(currentSearchText, false)
            searchView.clearFocus()
        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                scrollListener.resetPagination()
                currentSearchText = query!!
                onLoad(query, scrollListener.currentPage)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
        item.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                when (item?.itemId) {
                    R.id.search -> {
                        currentSearchText = null
                        scrollListener.resetPagination()
                        onLoad(currentSearchText, scrollListener.currentPage)
                    }
                }
                return true
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putString(CURRENT_SEARCH_KEY, currentSearchText)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        currentSearchText = savedInstanceState?.getString(CURRENT_SEARCH_KEY)
    }

    companion object {
        private const val CURRENT_SEARCH_KEY = "CURRENT_SEARCH_TEXT"
    }

    abstract fun onLoad(query: String?, nextPage: Long)
}
