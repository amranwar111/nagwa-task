package com.team.domain.domainRepo

import com.team.entities.videos.remote.response.VideosResponse
import io.reactivex.Single

interface IDomainRepo {
    /*----------------------------------------Remote----------------------------------------*/

    fun getVideos(): Single<VideosResponse>
}