package com.gblrod.radianthub.data.maps.mapper

import com.gblrod.radianthub.data.maps.remote.dto.MapsDto
import com.gblrod.radianthub.domain.maps.model.Maps

fun MapsDto.toDomain() = Maps(
    uuid = uuid,
    name = displayName,
    splash = splash,
    icon = displayIcon,
    coordinates = coordinates,
    callouts = callouts?.map { it.toDomain() }.orEmpty()
)