package com.srj.flobiz.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.srj.flobiz.data.ImageResponse
import com.srj.flobiz.databinding.HomeFragmentBinding
import com.srj.flobiz.network.utils.Resource
import com.srj.flobiz.network.utils.Status
import com.srj.flobiz.ui.viewmodel.HomeViewModel
import com.srj.flobiz.utils.*
import com.srj.flobiz.utils.adapter.ImageListAdapter
import kotlin.random.Random

class HomeFragment : Fragment() {

    private val viewModel by viewModels<HomeViewModel>()
    private lateinit var binding: HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.layoutManager = layoutManager

        setObservers()

        binding.closeButton.setOnClickListener {
            viewModel.setUserPreference(requireContext().applicationContext, true)
            hideBanner(binding.closeButton, binding.bannerCardView)
        }
    }

    //observer for livedata
    private fun setObservers() {
        viewModel.photos.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.ERROR -> {
                    toast(requireContext(), "Error!, Please re-open")
                }
                Status.SUCCESS -> {
                    hideProgressBar(binding.progressBar)
                    if (viewModel.getUserPreference(requireContext().applicationContext)) {
                        hideBanner(binding.closeButton, binding.bannerCardView)
                    } else
                        showBanner(binding.closeButton, binding.bannerCardView)
                    loadData(it)
                }
                Status.LOADING -> {
                    hideBanner(binding.closeButton, binding.bannerCardView)
                    showProgressBar(binding.progressBar)
                }
            }
        }
    }

    //load data in recyclerView and banner
    private fun loadData(resource: Resource<ImageResponse>) {
        val adapter = ImageListAdapter(requireContext(), resource.data!!.photos)
        val lastIndex = resource.data.photos.lastIndex
        binding.recyclerView.adapter = adapter
        Glide.with(requireContext())
            .load(
                resource
                    .data
                    .photos[Random.nextInt(0, lastIndex)]
                    .src.landscape
            )
            .into(binding.shapeAbleImageView)
    }

}