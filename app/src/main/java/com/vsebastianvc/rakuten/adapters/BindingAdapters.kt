package com.vsebastianvc.rakuten.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.vsebastianvc.rakuten.R
import com.vsebastianvc.rakuten.utils.PhotoUtils.Companion.loadImageWithGlide

/**
 * Created by Sebastian on 11/29/2020.
 */
@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(imageView: ImageView, imageUrl: String) {
    if (imageUrl.isNotBlank()) {
        loadImageWithGlide(imageView, imageUrl)
    } else {
        loadImageWithGlide(imageView, imageUrl, R.drawable.shape_gradient_top_background)
    }
}