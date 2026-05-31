package com.gblrod.radianthub.ui.features.cards.state

import com.gblrod.radianthub.domain.cards.model.Card

sealed class CardsUiState {
    object Loading : CardsUiState()

    data class Success(
        val cards: List<Card>
    ) : CardsUiState()

    data class Error(
        val messageResId: Int,
        val code: Int? = null
    ) : CardsUiState()
}