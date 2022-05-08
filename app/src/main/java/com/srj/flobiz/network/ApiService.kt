package com.srj.flobiz.network

import com.srj.flobiz.data.ImageResponse
import com.srj.flobiz.data.apiKey
import com.srj.flobiz.network.utils.Resource
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService : NetworkModule {
    @Headers("authorization:$apiKey")
    @GET("curated")
    suspend fun getImages( @Query("page")page: String,@Query("per_page") per_page: String): Response<ImageResponse>

}