package com.arctouch.codechallenge.internal.extensions

import android.app.Activity
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import com.arctouch.codechallenge.MyApplication
import com.arctouch.codechallenge.R
import kotlinx.android.synthetic.main.details_activity.*

val Activity.app: MyApplication
    get() = application as MyApplication

fun AppCompatActivity.displayHomeAsUpEnabled() {
    ViewCompat.setTransitionName(app_bar_layout, getString(R.string.app_name))
    supportPostponeEnterTransition()
    setSupportActionBar(toolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
}

inline fun <reified T : ViewModel> FragmentActivity.viewModel(factory: ViewModelProvider.Factory): T {
    return ViewModelProviders.of(this, factory)[T::class.java]
}