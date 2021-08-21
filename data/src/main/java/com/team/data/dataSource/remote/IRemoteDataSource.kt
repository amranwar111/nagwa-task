package com.team.data.dataSource.remote

import com.team.entities.videos.remote.response.VideosResponse
import io.reactivex.Single

interface IRemoteDataSource {

    fun getVideos(): Single<VideosResponse>
}