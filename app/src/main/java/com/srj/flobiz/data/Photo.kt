package com.srj.flobiz.data


import com.google.gson.annotations.SerializedName

data class Photo(
    @SerializedName("alt")
    var alt: String,

    @SerializedName("id")
    val id: Int,
    @SerializedName("photographer")
    val photographer: String,
    @SerializedName("src")
    val src: Src,
    @SerializedName("url")
    val url: String,

    )