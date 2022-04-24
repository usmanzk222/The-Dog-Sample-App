package com.usman.mvvmsample.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import com.usman.mvvmsample.R


@BindingAdapter("setImage", requireAll = false)
fun ImageView.setImage(imagePath: String?) {
    if (imagePath.isNullOrEmpty())
        setImageResource(R.drawable.ic_launcher_foreground)
    else
        Picasso.get()
            .load(imagePath)
            .placeholder(android.R.drawable.screen_background_light)
            .into(this)
}