package com.arctouch.codechallenge.presentation.common

import android.arch.lifecycle.ViewModel

abstract class AbstractPaginatedSearchViewModel : ViewModel() {

    companion object {
        const val INITIAL_PAGE: Long = 1
    }

    fun <T> Resource<MutableList<T>>.append(valuesToAppend: List<T>, page: Long) : MutableList<T> {
        val dataSet = mutableListOf<T>()
        when (data != null && page != INITIAL_PAGE) {
            true -> {
                dataSet.addAll(data)
                dataSet.addAll(valuesToAppend)
            } else -> dataSet.addAll(valuesToAppend)
        }
        return dataSet
    }

    abstract fun load(query: String? = null, page: Long = INITIAL_PAGE)
}