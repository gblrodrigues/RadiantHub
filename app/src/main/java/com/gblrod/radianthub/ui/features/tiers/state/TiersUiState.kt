package com.gblrod.radianthub.ui.features.tiers.state

import com.gblrod.radianthub.ui.features.tiers.model.TierGroup

sealed class TiersUiState {
    object Loading : TiersUiState()

    data class Success(
        val tiers: List<TierGroup>
    ) : TiersUiState()

    data class Error(
        val messageResId: Int,
        val code: Int? = null
    ) : TiersUiState()
}