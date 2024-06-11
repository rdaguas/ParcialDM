package com.aguas_roberto.parcial.logic.userCases

import com.aguas_roberto.parcial.data.network.endpoints.ArtistasEndpoint
import com.aguas_roberto.parcial.data.network.repository.RetrofitBase
import com.aguas_roberto.parcial.ui.core.toArtistasNobelUI
import com.aguas_roberto.parcial.ui.entities.ArtistasDataUI


class GetArtistasUserCase {
    suspend operator fun invoke(): Result<List<ArtistasDataUI>> {

        var items = ArrayList<ArtistasDataUI>()
        var response = RetrofitBase.returnBaseRetrofitArtistas()
            .create(ArtistasEndpoint::class.java)
            .getArtistas()
        return if (response.isSuccessful) {
            response.body()?.resultMessages?.forEach {
                items.add(it.toArtistasNobelUI())
            }
            Result.success(items)
        } else {
            Result.failure(Exception("Error en la API"))
        }
    }
}