package com.vsebastianvc.rakuten.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.vsebastianvc.rakuten.R
import com.vsebastianvc.rakuten.models.Photo

/**
 * Created by Sebastian on 11/29/2020.
 */
class PhotoUtils {

    companion object {
        fun constructPhotoURL(photo: Photo): String {
            if (photo.server.isNotEmpty() && photo.id.isNotEmpty() && photo.secret.isNotEmpty()) {
                return "https://live.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg"
            }
            return ""
        }

        fun loadImageWithGlide(imageView: ImageView, imageUrl: String, resourceId: Int? = null) {
            if (resourceId == null) {
                Glide.with(imageView.context)
                        .load(imageUrl)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imageView)
            } else {
                Glide.with(imageView.context)
                        .load(imageUrl)
                        .placeholder(R.drawable.shape_gradient_top_background)
                        .centerCrop()
                        .into(imageView)
            }
        }
    }
}