package com.team.entities.videos

data class Video(
    val id: Int?,
    val type: String?,
    val url: String?,
    val name: String?,
    var downloaded: Boolean = false,
    var downloading: Boolean = false,
)
