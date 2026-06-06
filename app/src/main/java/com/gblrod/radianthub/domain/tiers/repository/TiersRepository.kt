package com.gblrod.radianthub.domain.tiers.repository

import com.gblrod.radianthub.domain.tiers.model.Tier

interface TiersRepository {
    suspend fun getTiers() : List<Tier>
}