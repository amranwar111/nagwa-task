package com.team.data.dataSource.remote

import com.team.data.remote.IRemoteData
import com.team.entities.videos.remote.response.VideosResponse
import io.reactivex.Single
import javax.inject.Inject

class RemoteDataSource @Inject constructor(val iRemoteData: IRemoteData) : IRemoteDataSource {
    override fun getVideos(): Single<VideosResponse> {
        return iRemoteData.getVideos()
    }


}