package com.srj.flobiz.repository

import com.srj.flobiz.network.NetworkModule
import com.srj.flobiz.network.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class GetImageRepository : NetworkModule {


    suspend fun getImages(page: String, per_page: String) = withContext(Dispatchers.IO) {
        try {

            val result = apiService.getImages(page, per_page)
                Resource.success(result.body())
        }
        catch (e:Exception){
            e.message?.let { Resource.error(it,null) }
        }

    }
}