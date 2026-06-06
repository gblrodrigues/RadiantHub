package com.gblrod.radianthub.data.tiers.remote.api

import com.gblrod.radianthub.data.tiers.remote.dto.TiersResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface TiersApi {
    @GET(value = "v1/competitivetiers")
    suspend fun getTiers(
        @Query("language")
        language: String
    ): TiersResponseDto
}