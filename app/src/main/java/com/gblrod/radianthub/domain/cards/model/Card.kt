package com.gblrod.radianthub.domain.cards.model

data class Card(
    val uuid: String,
    val name: String,
    val largeArt: String?,
    val wideArt: String?,
    val smallArt: String?
)