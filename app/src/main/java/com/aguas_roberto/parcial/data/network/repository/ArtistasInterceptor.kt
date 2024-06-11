package com.aguas_roberto.parcial.data.network.repository

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class ArtistasInterceptor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
        val url = request
            .url.newBuilder()
            .build()
        val newRequest = request.newBuilder().url(url).build()

        Log.d("TAG", newRequest.url.toString())
        return chain.proceed(newRequest)
    }
}