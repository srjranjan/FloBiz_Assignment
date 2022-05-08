package com.srj.flobiz.ui.viewmodel

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.srj.flobiz.data.ImageResponse
import com.srj.flobiz.network.utils.Resource
import com.srj.flobiz.network.utils.Status
import com.srj.flobiz.repository.GetImageRepository
import kotlinx.coroutines.launch
import kotlin.random.Random


class HomeViewModel : ViewModel() {
    private var _photos = MutableLiveData<Resource<ImageResponse>>()
    val photos: MutableLiveData<Resource<ImageResponse>> = _photos
    private val getImageRepository = GetImageRepository()

    init {
        val random = Random.nextInt(1, 20).toString()
        getAllImages(random)
    }

    private fun getAllImages(random: String) {
        viewModelScope.launch {
            _photos.postValue(Resource.loading(null))
            val response = getImageRepository.getImages(random, "25")
            if (response != null) {
                if (response.status == Status.SUCCESS) {
                    _photos.postValue(Resource.success(response.data))
                } else
                    _photos.postValue(Resource.error(response.message.toString(), null))
            }

        }

    }

    fun setUserPreference(context: Context, userPref: Boolean) {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences("UserPref", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("show", userPref)
        editor.apply()
    }

    fun getUserPreference(context: Context): Boolean {
        val sharedPreferences = context.getSharedPreferences("UserPref", MODE_PRIVATE)
        return sharedPreferences.getBoolean("show", false)
    }

}