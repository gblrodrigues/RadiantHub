package com.gblrod.radianthub.data.maps.remote.api

import com.gblrod.radianthub.data.maps.remote.dto.MapsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MapsApi {
    @GET(value = "v1/maps")
    suspend fun getMaps(
        @Query("language")
        language: String
    ): MapsResponseDto
}