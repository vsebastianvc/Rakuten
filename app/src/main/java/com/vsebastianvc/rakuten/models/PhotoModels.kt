package com.vsebastianvc.rakuten.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import com.google.gson.annotations.SerializedName

/**
 * Created by Sebastian on 11/29/2020.
 */

@Parcelize
data class Photo(
    val id: String,
    val owner: String,
    val secret: String,
    val server: String,
    val farm: Int,
    val title: String,
    @SerializedName("ispublic")
    val isPublic: Int,
    @SerializedName("isfriend")
    val isFriend: Int,
    @SerializedName("isfamily")
    val isFamily: Int
) : Parcelable

data class PhotoList(
        val page: Int,
        val pages: Int,
        @SerializedName("perpage")
        val perPage: Int,
        val total: Int,
        val photo: List<Photo>
)

data class PhotoPage(
        val photos: PhotoList,
        val stat: String
)

