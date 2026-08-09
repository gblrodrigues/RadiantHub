package com.gblrod.radianthub.data.cards.mapper

import com.gblrod.radianthub.data.cards.remote.dto.CardsDto
import com.gblrod.radianthub.domain.cards.model.Card

fun CardsDto.toDomain() = Card(
    uuid = uuid,
    name = displayName,
    largeArt = largeArt,
    wideArt = wideArt,
    smallArt = smallArt
)