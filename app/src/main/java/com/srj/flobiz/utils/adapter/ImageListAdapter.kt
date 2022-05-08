package com.srj.flobiz.utils.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.srj.flobiz.data.Photo
import com.srj.flobiz.databinding.ImageLayoutBinding

class ImageListAdapter(val context: Context, private val imageList: List<Photo>) :
    RecyclerView.Adapter<ImageListAdapter.ImageListViewHolder>() {


    inner class ImageListViewHolder(private val binding: ImageLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(photo: Photo) {
            binding.photograpgher.text = photo.photographer
            binding.description.text = photo.alt.let {
                if (it == "") {
                    "No description available"
                } else
                    it
            }.toString()
            Glide.with(context).load(photo.src.tiny).into(binding.cardImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageListViewHolder {
        val binding = ImageLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageListViewHolder, position: Int) {
        holder.bindData(imageList[position])
    }

    override fun getItemCount(): Int {
        return imageList.size
    }
}

