package com.team.entities.videos

import com.team.entities.utils.EntityMapper
import com.team.entities.videos.remote.response.VideosResponse
import com.team.entities.videos.remote.response.VideosResponseItem
import javax.inject.Inject

class VideoMapper
@Inject
constructor() : EntityMapper<VideosResponseItem, Video> {

    override fun mapFromEntity(entity: VideosResponseItem): Video {
        return Video(
            id = entity.id,
            type = entity.type,
            url = entity.url,
            name = entity.name,
        )
    }

    override fun mapToEntity(domainModel: Video): VideosResponseItem {
        return VideosResponseItem(
            id = domainModel.id,
            type = domainModel.type,
            url = domainModel.url,
            name = domainModel.name,
        )
    }


    fun mapFromEntityList(entities: VideosResponse): List<Video>? {
        return entities.videosResponse?.map { mapFromEntity(it) }
    }

    fun mapToEntityList(entities: List<Video>): List<VideosResponseItem> {
        return entities.map { mapToEntity(it) }
    }
}


