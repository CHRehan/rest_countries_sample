package com.majority.assignment.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.majority.assignment.utilities.CircleTransform
import com.squareup.picasso.Picasso

@BindingAdapter("app:loadCircleImage")

fun ImageView.loadCircleImage(uri: String) {
    Picasso
        .get()
        .load(uri)
        .transform(CircleTransform())
        .into(this)


}


@BindingAdapter("app:loadFlag")
fun ImageView.loadImage(url: String?) {
    Picasso
        .get()
        .load(url)
        .into(this)

}