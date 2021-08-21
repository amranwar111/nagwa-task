package com.team.nagwa.ui.videos

import androidx.lifecycle.MutableLiveData
import com.team.core.utils.NetworkState
import com.team.domain.use_case.videos.VideosUseCase
import com.team.entities.videos.Video
import com.team.entities.videos.VideoMapper
import com.team.nagwa.base.BaseViewModel
import com.team.nagwa.ui.Dummy
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class VideoViewModel @Inject constructor(private val videosUseCase: VideosUseCase) :
    BaseViewModel() {

    private val videos = MutableLiveData<List<Video>>()

    @Inject
    lateinit var videoMapper: VideoMapper

    fun getVideos() {
        networkState.postValue(NetworkState.LOADING)
        addToDisposable(
            videosUseCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ videoResponse ->
                    networkState.postValue(NetworkState.LOADED)
                    videos.postValue(videoMapper.mapFromEntityList(videoResponse))
                }, {
                    videos.postValue(Dummy)
                    networkState.postValue(NetworkState.ERROR)
                    errorState.postValue(it.message)
                })
        )
    }

    fun getVideosLiveData() = videos
}