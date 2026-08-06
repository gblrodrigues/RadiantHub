package com.gblrod.radianthub.core.connectivity

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class RetryManager {
    private val _retryAll = MutableSharedFlow<Unit>(
        replay = 0,
        extraBufferCapacity = 1
    )

    val retryAll: SharedFlow<Unit> = _retryAll

    fun retry() {
        _retryAll.tryEmit(Unit)
    }
}