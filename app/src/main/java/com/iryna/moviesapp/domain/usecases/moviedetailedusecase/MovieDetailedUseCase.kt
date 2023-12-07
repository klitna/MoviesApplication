package com.iryna.moviesapp.domain.usecases.moviedetailedusecase

import android.util.Log
import com.iryna.moviesapp.data.datasource.dto.toDomainModel
import com.iryna.moviesapp.domain.entity.workerdetailedentity.MovieDetailedEntity
import com.iryna.moviesapp.domain.entity.workerdetailedentity.MovieDetailedRequestEntity
import com.iryna.moviesapp.domain.repository.IMoviesRepository
import com.iryna.moviesapp.util.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MovieDetailedUseCase @Inject constructor(
    private val repository: IMoviesRepository
) {
    operator fun invoke(params: Params?): Flow<ResponseState<MovieDetailedEntity>> = flow{
        try {
            emit(ResponseState.Loading())
            val workerDetailed =
                params?.movieDetailedRequest?.let { repository.getMovieDetailed(it.id).toDomainModel() }
            emit(ResponseState.Success(workerDetailed))
        }
        catch(e: HttpException){
            emit(ResponseState.Error(e.localizedMessage ?: "Error Occured"))

        }
        catch(e: IOException){
            Log.i("ioexception", e.toString())
            emit(ResponseState.Error("Error Occured"))
        }
    }

    class Params private constructor(val movieDetailedRequest: MovieDetailedRequestEntity) {
        companion object {
            fun getMovieDetailed(movieDetailedRequest: MovieDetailedRequestEntity): Params {
                return Params(movieDetailedRequest)
            }
        }
    }
}