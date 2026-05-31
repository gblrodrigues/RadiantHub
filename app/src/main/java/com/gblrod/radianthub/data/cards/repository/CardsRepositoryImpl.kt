package com.gblrod.radianthub.data.cards.repository

import com.gblrod.radianthub.data.cards.mapper.toDomain
import com.gblrod.radianthub.data.cards.remote.api.CardsApi
import com.gblrod.radianthub.domain.cards.model.Card
import com.gblrod.radianthub.domain.cards.repository.CardsRepository

class CardsRepositoryImpl(
    private val api: CardsApi
) : CardsRepository {
    override suspend fun getCards(): List<Card> {
        return api
            .getCards()
            .data
            .filter { !it.largeArt.isNullOrBlank() }
            .map { it.toDomain() }
    }
}