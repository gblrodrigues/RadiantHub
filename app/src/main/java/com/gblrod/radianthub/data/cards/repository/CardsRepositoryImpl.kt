package com.gblrod.radianthub.data.cards.repository

import com.gblrod.radianthub.core.localization.ApiLanguageProvider
import com.gblrod.radianthub.data.cards.mapper.toDomain
import com.gblrod.radianthub.data.cards.remote.api.CardsApi
import com.gblrod.radianthub.domain.cards.model.Card
import com.gblrod.radianthub.domain.cards.repository.CardsRepository

class CardsRepositoryImpl(
    private val api: CardsApi,
    private val languageProvider: ApiLanguageProvider
) : CardsRepository {
    override suspend fun getCards(): List<Card> {
        return api
            .getCards(
                language = languageProvider.getLanguage()
            )
            .data
            .filter { !it.largeArt.isNullOrBlank() }
            .map { it.toDomain() }
    }
}