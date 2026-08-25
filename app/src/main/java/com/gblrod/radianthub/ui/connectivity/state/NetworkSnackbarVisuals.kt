package com.gblrod.radianthub.ui.connectivity.state

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarVisuals
import com.gblrod.radianthub.ui.connectivity.model.NetworkSnackbarType

class NetworkSnackbarVisuals(
    val type: NetworkSnackbarType,
    override val duration: SnackbarDuration
) : SnackbarVisuals {
    override val message: String = ""
    override val actionLabel: String? = null
    override val withDismissAction: Boolean = false
}