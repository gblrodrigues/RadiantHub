package com.gblrod.radianthub.data.maps.repository

import com.gblrod.radianthub.data.maps.mapper.toDomain
import com.gblrod.radianthub.data.maps.remote.api.MapsApi
import com.gblrod.radianthub.domain.maps.model.Maps
import com.gblrod.radianthub.domain.maps.repository.MapsRepository

class MapsRepositoryImpl(
    private val api: MapsApi
) : MapsRepository {
    override suspend fun getMaps(): List<Maps> {
        return api
            .getMaps()
            .data
            .filter { !it.displayIcon.isNullOrBlank() && !it.splash.isNullOrBlank() }
            .map { it.toDomain() }
    }
}