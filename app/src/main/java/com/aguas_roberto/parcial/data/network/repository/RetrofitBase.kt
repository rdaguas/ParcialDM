package com.aguas_roberto.parcial.data.network.repository

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
object RetrofitBase {

    //https://2c5f17142d37474d9984c8e631502e0b.api.mockbin.io/
        fun returnBaseRetrofitArtistas(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://2c5f17142d37474d9984c8e631502e0b.api.mockbin.io")
            .addConverterFactory(GsonConverterFactory.create())
            .client(apiClientArtistas())
            .build()
    }

    private fun apiClientArtistas(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(ArtistasInterceptor())
        .build()

}