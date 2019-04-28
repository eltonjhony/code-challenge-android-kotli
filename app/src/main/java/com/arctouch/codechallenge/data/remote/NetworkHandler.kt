package com.arctouch.codechallenge.data.remote

import android.content.Context
import com.arctouch.codechallenge.internal.extensions.networkInfo
import javax.inject.Inject

class NetworkHandler
@Inject constructor(private val context: Context) {
    val isConnected get() = context.networkInfo?.isConnectedOrConnecting
}