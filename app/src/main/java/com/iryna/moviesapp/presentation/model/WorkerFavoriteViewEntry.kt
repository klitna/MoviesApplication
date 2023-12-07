package com.iryna.moviesapp.presentation.model

import android.os.Parcelable
import com.iryna.moviesapp.domain.entity.WorkerFavoriteEntity
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WorkerFavoriteViewEntry(
    val color: String?,
    val food: String?,
    val randomString: String?,
    val song: String?
): Parcelable {
    fun toEntity(): WorkerFavoriteEntity {
        return WorkerFavoriteEntity(
            color = color ?: "",
            food = food?: "",
            randomString = randomString ?: "",
            song = song ?: ""
        )
    }

    constructor(workerFavoriteEntity: WorkerFavoriteEntity) : this(
        color = workerFavoriteEntity.color ?: "",
        food = workerFavoriteEntity.food?: "",
        randomString = workerFavoriteEntity.randomString ?: "",
        song = workerFavoriteEntity.song ?: ""
    )
}