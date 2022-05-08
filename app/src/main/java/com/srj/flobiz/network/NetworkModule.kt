package com.srj.flobiz.network

import com.srj.flobiz.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface NetworkModule {
    val okHttpClient: OkHttpClient
        get() = if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
        } else {
            OkHttpClient
                .Builder()
                .build()
        }

    //initialize retrofit
    val retrofit: Retrofit
        get() = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.pexels.com/v1/")
            .client(okHttpClient)
            .build()
    val apiService: ApiService
        get() = retrofit.create(ApiService::class.java)
}