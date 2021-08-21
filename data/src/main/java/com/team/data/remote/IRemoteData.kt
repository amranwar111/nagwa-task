package com.team.data.remote

import com.team.entities.videos.remote.response.VideosResponse
import io.reactivex.Single
import retrofit2.http.GET

interface IRemoteData {

     @GET("movies")
     fun getVideos(): Single<VideosResponse>
}