package com.arctouch.codechallenge.presentation.common

data class Resource<out T> constructor(
        val state: ResourceState,
        val data: T? = null,
        val throwable: Throwable? = null
)