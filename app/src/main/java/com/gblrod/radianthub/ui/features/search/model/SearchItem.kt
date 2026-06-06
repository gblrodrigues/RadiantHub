package com.gblrod.radianthub.ui.features.search.model

data class SearchItem(
    val uuid: String,
    val title: String,
    val imageUrl: String,
    val type: SearchType,
    val tierId: Int? = null
)