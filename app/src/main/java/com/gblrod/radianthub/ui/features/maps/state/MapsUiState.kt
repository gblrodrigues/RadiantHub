package com.gblrod.radianthub.ui.features.maps.state

import com.gblrod.radianthub.domain.maps.model.Maps

sealed class MapsUiState {
    object Loading : MapsUiState()

    data class Success(
        val maps: List<Maps>
    ) : MapsUiState()

    data class Error(
        val messageResId: Int,
        val code: Int? = null
    ) : MapsUiState()
}