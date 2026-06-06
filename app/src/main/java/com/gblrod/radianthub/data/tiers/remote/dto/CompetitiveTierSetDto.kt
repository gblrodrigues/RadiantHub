package com.gblrod.radianthub.data.tiers.remote.dto

data class CompetitiveTierSetDto(
    val uuid: String,
    val assetObjectName: String,
    val tiers: List<TierDto>
)