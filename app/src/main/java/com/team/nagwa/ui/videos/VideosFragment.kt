package com.team.nagwa.ui.videos

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.team.core.utils.NetworkState
import com.team.core.utils.network.NetworkUtils
import com.team.entities.videos.Video
import com.team.nagwa.databinding.FragmentVideosBinding
import dagger.hilt.android.AndroidEntryPoint
import com.downloader.*
import com.team.nagwa.ui.videos.adapters.VideoAdapter

@AndroidEntryPoint
class VideosFragment : Fragment(), VideoAdapter.OnItemClickListener {

    private lateinit var binding: FragmentVideosBinding

    private val VM: VideoViewModel by viewModels()

    private lateinit var videoAdapter: VideoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentVideosBinding.inflate(inflater, container, false)
        downloaderConfig()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        videoAdapter = VideoAdapter(requireContext(), this)
        binding.rvVideos.adapter = videoAdapter

        getErrorAndNetworkState()
        callVideosApi()
        observeData()
    }

    private fun callVideosApi() {
        if (NetworkUtils.getNetworkUtils().isConnected()) {
            VM.getVideos()
        } else {
            Log.e("TAG", "callVideosApi: Error in internet")
        }
    }

    private fun observeData() {
        VM.getVideosLiveData().observe(viewLifecycleOwner, { videos ->
            videoAdapter.setData(videos as MutableList<Video>)
            Log.e("TAG", "observeData: $videos")
        })
    }

    private fun getErrorAndNetworkState() {
        VM.getNetworkState().observe(viewLifecycleOwner, Observer {
            if (it == NetworkState.LOADING) {
                binding.pbVideos.visibility = View.VISIBLE

                if (it == NetworkState.ERROR) {
                    binding.pbVideos.visibility = View.GONE
                }
            } else {
                binding.pbVideos.visibility = View.GONE
            }
        })

        VM.getError().observe(viewLifecycleOwner, Observer {
            binding.pbVideos.visibility = View.GONE
        })
    }

    private fun downloaderConfig() {
        PRDownloader.initialize(requireContext())
    }

    override fun onItemClicked(item: Video) {


    }
}