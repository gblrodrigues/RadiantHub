package com.gblrod.radianthub.ui.features.search.state

import com.gblrod.radianthub.ui.features.search.model.SearchItem

sealed class SearchUiState {
    object Loading : SearchUiState()

    data class Success(
        val query: String,
        val results: List<SearchItem>
    ) : SearchUiState()

    data class Error(
        val messageResId: Int,
        val code: Int? = null
    ) : SearchUiState()
}