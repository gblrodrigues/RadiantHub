package com.gblrod.radianthub.data.tiers.remote.dto

data class TierDto(
    val tier: Int,
    val tierName: String,
    val division: String,
    val divisionName: String,
    val smallIcon: String?,
    val rankTriangleDownIcon: String?
)