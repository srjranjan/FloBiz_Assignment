package com.srj.flobiz.data

import com.google.gson.annotations.SerializedName

data class Src(
    @SerializedName("landscape")
    val landscape: String,
    val small: String,
    @SerializedName("tiny")
    val tiny: String
)