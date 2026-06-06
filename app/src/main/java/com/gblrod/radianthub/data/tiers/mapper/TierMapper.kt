package com.gblrod.radianthub.data.tiers.mapper

import com.gblrod.radianthub.data.tiers.remote.dto.TierDto
import com.gblrod.radianthub.domain.tiers.model.Tier

fun TierDto.toDomain() = Tier(
    tier = tier,
    tierName = tierName,
    division = division,
    divisionName = divisionName,
    icon = smallIcon
)