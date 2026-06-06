package com.gblrod.radianthub.domain.tiers.model

data class Tier(
    val tier: Int,
    val tierName: String,
    val division: String,
    val divisionName: String,
    val icon: String?
)