package com.srj.flobiz.data


import com.google.gson.annotations.SerializedName

data class ImageResponse(
    @SerializedName("photos")
    val photos: List<Photo>
)