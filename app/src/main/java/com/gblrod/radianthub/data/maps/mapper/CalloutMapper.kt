package com.gblrod.radianthub.data.maps.mapper

import com.gblrod.radianthub.data.maps.remote.dto.CalloutDto
import com.gblrod.radianthub.domain.maps.model.Callout

fun CalloutDto.toDomain() =
    Callout(
        regionName = regionName.orEmpty(),
        superRegionName = superRegionName.orEmpty()
    )