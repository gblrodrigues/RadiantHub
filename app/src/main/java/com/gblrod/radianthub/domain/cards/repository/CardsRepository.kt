package com.gblrod.radianthub.domain.cards.repository

import com.gblrod.radianthub.domain.cards.model.Card

interface CardsRepository {
    suspend fun getCards(): List<Card>
}