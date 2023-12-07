package com.iryna.moviesapp.domain.entity

import com.iryna.moviesapp.data.datasource.dto.WorkerFavoriteDto

data class WorkerFavoriteEntity (
    val color: String?,
    val food: String?,
    val randomString: String?,
    val song: String?
)

fun WorkerFavoriteDto.toDomainModel(): WorkerFavoriteEntity {
    return WorkerFavoriteEntity(
        color = this.color ?: "",
        food = this.food ?: "",
        randomString = this.random_string ?: "",
        song = song ?: ""
    )
}

