package com.iryna.moviesapp.di

import com.iryna.moviesapp.data.datasource.MoviesApi
import com.iryna.moviesapp.data.repository.MoviesRepositoryImpl
import com.iryna.moviesapp.domain.repository.IMoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MoviesModule {

    @Provides
    @ApiEndpoint
    fun provideApiEndPoint(): String = "https://api.themoviedb.org/"

    @Provides
    @ApiKey
    fun provideApiKey(): String = "13911f205ca8e5baa5a413f5e9a9796d"

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        queryInterceptor: QueryInterceptor,
        loggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(queryInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideMoviesApi(
        @ApiEndpoint apiEndPoint: String,
        okHttpClient: OkHttpClient
    ): MoviesApi {
        return Retrofit.Builder()
            .baseUrl(apiEndPoint)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(MoviesApi::class.java)
    }

    @Provides
    @Singleton
    fun providesMoviesRepository(api: MoviesApi): IMoviesRepository {
        return MoviesRepositoryImpl(api)
    }
}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class ApiKey

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class ApiEndpoint
