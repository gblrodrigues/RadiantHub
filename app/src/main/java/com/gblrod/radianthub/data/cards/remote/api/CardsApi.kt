package com.gblrod.radianthub.data.cards.remote.api

import com.gblrod.radianthub.data.cards.remote.dto.CardsResponseDto
import retrofit2.http.GET

interface CardsApi {
    @GET(value = "v1/playercards")
    suspend fun getCards(): CardsResponseDto
}