package com.gblrod.radianthub.core.connectivity

import kotlinx.coroutines.flow.Flow

interface NetworkChecker {
    val isConnected: Flow<Boolean>
}