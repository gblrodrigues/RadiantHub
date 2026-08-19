package com.gblrod.radianthub.ui.features.search.state

import com.gblrod.radianthub.ui.features.search.model.SearchItem

sealed interface SearchUiState {
    data class Loading(
        val query: String = ""
    ) : SearchUiState

    data class Success(
        val query: String,
        val results: List<SearchItem>
    ) : SearchUiState

    data class Error(
        val query: String,
        val messageResId: Int,
        val code: Int? = null
    ) : SearchUiState
}