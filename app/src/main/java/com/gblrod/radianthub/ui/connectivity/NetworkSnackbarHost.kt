package com.gblrod.radianthub.ui.connectivity

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Wifi
import androidx.compose.material.icons.rounded.WifiOff
import androidx.compose.material3.Icon
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.gblrod.radianthub.R
import com.gblrod.radianthub.ui.connectivity.model.NetworkSnackbarType
import com.gblrod.radianthub.ui.connectivity.state.NetworkSnackbarVisuals
import com.gblrod.radianthub.ui.theme.OfflineBackground
import com.gblrod.radianthub.ui.theme.OnlineBackground

@Composable
fun NetworkSnackbarHost(
    snackbarHostState: SnackbarHostState
) {
    val noConnection = stringResource(id = R.string.no_connection)
    val connectionRestored = stringResource(id = R.string.connection_restored)

    SnackbarHost(
        hostState = snackbarHostState
    ) { data ->

        val visuals = data.visuals

        if (visuals is NetworkSnackbarVisuals) {
            val message = when (visuals.type) {
                NetworkSnackbarType.OFFLINE -> noConnection
                NetworkSnackbarType.ONLINE -> connectionRestored
            }

            val isOffline = visuals.type == NetworkSnackbarType.OFFLINE

            Snackbar(
                containerColor = if (isOffline) {
                    OfflineBackground
                } else {
                    OnlineBackground
                }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = if (isOffline) Icons.Rounded.WifiOff else Icons.Rounded.Wifi,
                        contentDescription = null,
                        tint = Color.White
                    )

                    Text(
                        text = message,
                        color = Color.White
                    )
                }
            }

        } else {
            Snackbar(
                snackbarData = data
            )
        }
    }
}