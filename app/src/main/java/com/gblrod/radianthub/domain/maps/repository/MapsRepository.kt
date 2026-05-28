package com.gblrod.radianthub.domain.maps.repository

import com.gblrod.radianthub.domain.maps.model.Maps

interface MapsRepository {
    suspend fun getMaps(): List<Maps>
}