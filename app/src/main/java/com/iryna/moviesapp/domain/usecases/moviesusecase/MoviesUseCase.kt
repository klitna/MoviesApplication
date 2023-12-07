package com.iryna.moviesapp.domain.usecases.moviesusecase

import android.util.Log
import com.iryna.moviesapp.data.datasource.dto.toDomainModel
import com.iryna.moviesapp.domain.entity.workersentity.MoviesListEntity
import com.iryna.moviesapp.domain.entity.workersentity.MoviesRequest
import com.iryna.moviesapp.domain.repository.IMoviesRepository
import com.iryna.moviesapp.util.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MoviesUseCase @Inject constructor(
    private val repository: IMoviesRepository
) {
    operator fun invoke(params: Params?): Flow<ResponseState<MoviesListEntity>> = flow{
        try {
            emit(ResponseState.Loading())

            val workers = params?.workersRequest?.let { repository.getMovies(it).toDomainModel() }
            emit(ResponseState.Success(workers))
        }
        catch(e: HttpException){
            emit(ResponseState.Error(e.localizedMessage ?: "Error Occured"))

        }
        catch(e: IOException){
            Log.i("ioexception", e.toString())
            emit(ResponseState.Error("Error Occured"))
        }
    }

    class Params private constructor(val workersRequest: MoviesRequest) {
        companion object {
            fun getMovies(workersRequest: MoviesRequest): Params {
                return Params(workersRequest)
            }
        }
    }
}