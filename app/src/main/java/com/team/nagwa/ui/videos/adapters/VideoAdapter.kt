package com.team.nagwa.ui.videos.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.downloader.OnDownloadListener
import com.downloader.PRDownloader
import com.team.entities.videos.Video
import com.team.nagwa.R
import com.team.nagwa.databinding.VideoItemBinding
import com.team.nagwa.ui.createFile
import javax.inject.Inject

class VideoAdapter @Inject constructor(
    private val context: Context,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: MutableList<Video> = mutableListOf()
    private lateinit var binding: VideoItemBinding
    private var isDownLoading = false

    interface OnItemClickListener {
        fun onItemClicked(item: Video)
    }


    @SuppressLint("NotifyDataSetChanged")
    fun setData(items: MutableList<Video>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val viewHolder: RecyclerView.ViewHolder?

        binding = VideoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        viewHolder = MainViewHolder(binding)
        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        val mainViewHolder = holder as MainViewHolder
        mainViewHolder.bindData(item, listener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class MainViewHolder(private val binding: VideoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(item: Video, listener: OnItemClickListener) {
            binding.txVideoName.text = item.name
            if (item.type == "VIDEO") {
                Glide.with(context).load(R.drawable.ic_video)
                    .into(binding.ivVideo)
            } else {
                Glide.with(context).load(R.drawable.ic_pdf)
                    .into(binding.ivVideo)
            }
            binding.btnVideo.setOnClickListener {
                if (!isDownLoading)
                    downloadFile(binding, item)
            }
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    private fun downloadFile(binding: VideoItemBinding, item: Video) {
        PRDownloader.download(
            item.url,
            createFile(context, item.name.toString())?.absolutePath, item.name
        )
            .build()
            .setOnStartOrResumeListener {
                binding.seekBar.visibility = View.VISIBLE
            }
            .setOnProgressListener {
                binding.btnVideo.text = context.getString(R.string.downloading)
                binding.btnVideo.isEnabled = false
                binding.seekBar.max = it.totalBytes.toInt()
                binding.seekBar.progress = it.currentBytes.toInt()
                isDownLoading = true
            }
            .start(object : OnDownloadListener {
                override fun onDownloadComplete() {
                    binding.seekBar.visibility = View.GONE
                    binding.btnVideo.text = context.getString(R.string.downloaded)
                    binding.btnVideo.isEnabled = false
                    isDownLoading = false
                }

                override fun onError(error: com.downloader.Error?) {
                }

            })
    }
}