package com.gblrod.radianthub.data.tiers.repository

import com.gblrod.radianthub.core.localization.ApiLanguageProvider
import com.gblrod.radianthub.data.tiers.mapper.toDomain
import com.gblrod.radianthub.data.tiers.remote.api.TiersApi
import com.gblrod.radianthub.domain.tiers.model.Tier
import com.gblrod.radianthub.domain.tiers.repository.TiersRepository

class TiersRepositoryImpl(
    private val api: TiersApi,
    private val languageProvider: ApiLanguageProvider
) : TiersRepository {
    override suspend fun getTiers(): List<Tier> {

        val response = api.getTiers(
            language = languageProvider.getLanguage()
        )

        return response
            .data
            .last()
            .tiers
            .map { it.toDomain() }
    }
}