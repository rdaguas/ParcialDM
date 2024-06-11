package com.aguas_roberto.parcial.data.network.endpoints

import com.aguas_roberto.parcial.data.network.entities.DataArtistas
import retrofit2.Response
import retrofit2.http.GET

interface ArtistasEndpoint {

    @GET("./")
    suspend fun getArtistas(): Response<DataArtistas>

}