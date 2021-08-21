package com.team.entities.videos.remote.response

import com.google.gson.annotations.SerializedName

data class VideosResponse(

	@field:SerializedName("VideosResponse")
	val videosResponse: List<VideosResponseItem>? = null
)

data class VideosResponseItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)
