package com.arctouch.codechallenge.presentation.common

import android.support.v7.app.AppCompatActivity
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.data.exception.NoNetworkException
import com.arctouch.codechallenge.internal.extensions.toast
import com.arctouch.codechallenge.internal.di.modules.ViewModelFactory
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    @Inject lateinit var viewModelFactory: ViewModelFactory

    internal fun showErrorFor(throwable: Throwable?) {
        when (throwable) {
            is NoNetworkException -> toast(R.string.no_network_error)
            else -> toast(R.string.generic_error)
        }
    }
}
