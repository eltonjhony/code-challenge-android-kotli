package com.arctouch.codechallenge.internal.extensions

import android.app.Activity
import android.support.annotation.StringRes
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import com.arctouch.codechallenge.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadFromUrl(url: String) {
    Glide.with(this.context)
            .load(url)
            .apply(RequestOptions().placeholder(R.drawable.ic_image_placeholder))
            .into(this)
}

fun Activity.toast(@StringRes msgResource: Int) {
    Toast.makeText(this, getString(msgResource), Toast.LENGTH_LONG).show()
}

fun ProgressBar.visibility(active: Boolean) {
    visibility = if (active) View.VISIBLE else View.GONE
}