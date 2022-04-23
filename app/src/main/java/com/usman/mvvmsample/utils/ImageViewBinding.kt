package com.usman.mvvmsample.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.usman.mvvmsample.R


@BindingAdapter("setImage", requireAll = false)
fun ImageView.setImage(imagePath: String?) {
    if (imagePath.isNullOrEmpty())
        setImageResource(R.drawable.ic_launcher_foreground)
    else
        Glide.with(this).load(imagePath).placeholder(R.drawable.ic_launcher_foreground).into(this)

}