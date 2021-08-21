package com.team.data.repo

import com.team.data.dataSource.remote.IRemoteDataSource
import com.team.domain.domainRepo.IDomainRepo
import com.team.entities.videos.remote.response.VideosResponse
import io.reactivex.Single
import javax.inject.Inject

class Repo @Inject constructor(
    private val iRemoteDataSource: IRemoteDataSource,
) : IDomainRepo {
    /*----------------------------------------Remote----------------------------------------*/

    override fun getVideos(): Single<VideosResponse> {
        return iRemoteDataSource.getVideos()
    }

}