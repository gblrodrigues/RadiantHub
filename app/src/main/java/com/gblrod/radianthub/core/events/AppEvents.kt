package com.gblrod.radianthub.core.events

import kotlinx.coroutines.flow.MutableSharedFlow

object AppEvents {
    val languageChanged = MutableSharedFlow<Unit>()
}