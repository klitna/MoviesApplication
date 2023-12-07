package com.iryna.moviesapp.di

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class QueryInterceptor @Inject constructor(@ApiKey private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalUrl = original.url

        val url = originalUrl
            .newBuilder()
            .addQueryParameter("api_key", apiKey)
            .build()

        val request = original.newBuilder()
            .url(url)
            .build()

        return chain.proceed(request)
    }
}