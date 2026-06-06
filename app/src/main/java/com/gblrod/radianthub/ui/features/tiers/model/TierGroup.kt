package com.gblrod.radianthub.ui.features.tiers.model

import com.gblrod.radianthub.domain.tiers.model.Tier

data class TierGroup(
    val rankName: String,
    val tiers: List<Tier>
)