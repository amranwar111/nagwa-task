package com.team.domain.use_case.videos

import com.team.domain.domainRepo.IDomainRepo
import com.team.domain.excuter.PostExecutionThread
import com.team.domain.excuter.ThreadExecutor
import com.team.domain.utils.SingleUseCase
import com.team.entities.videos.remote.response.VideosResponse
import io.reactivex.Single
import javax.inject.Inject

class VideosUseCase @Inject constructor(
    private val repo: IDomainRepo,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : SingleUseCase<VideosResponse, Unit>(threadExecutor, postExecutionThread) {
    override fun buildUseCaseObservable(params: Unit?): Single<VideosResponse> {

        return repo.getVideos()
    }
}