package com.srj.flobiz.utils

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.google.android.material.card.MaterialCardView
import com.google.android.material.imageview.ShapeableImageView

fun hideBanner(
    closeButton: ShapeableImageView,
    bannerCardView: MaterialCardView,
) {
    closeButton.visibility = View.GONE
    bannerCardView.visibility = View.GONE
}

fun showBanner(
    closeButton: ShapeableImageView,
    bannerCardView: MaterialCardView,
) {
    closeButton.visibility = View.VISIBLE
    bannerCardView.visibility = View.VISIBLE
}

fun toast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun showProgressBar(progressBar: ProgressBar) {
    progressBar.visibility = View.VISIBLE
}

fun hideProgressBar(progressBar: ProgressBar) {
    progressBar.visibility = View.GONE
}