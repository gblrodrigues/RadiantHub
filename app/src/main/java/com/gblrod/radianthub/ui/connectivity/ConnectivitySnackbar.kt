package com.gblrod.radianthub.ui.connectivity

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.gblrod.radianthub.R
import com.gblrod.radianthub.core.connectivity.NetworkChecker
import com.gblrod.radianthub.core.connectivity.RetryManager
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@Composable
fun ConnectivitySnackbar(
    snackbarHostState: SnackbarHostState
) {
    val networkChecker: NetworkChecker = koinInject()
    val retryManager: RetryManager = koinInject()

    val noConnection = stringResource(id = R.string.no_connection)
    val connectionRestored = stringResource(id = R.string.connection_restored)

    var previousState by remember { mutableStateOf<Boolean?>(null) }
    var snackbarJob by remember { mutableStateOf<Job?>(null) }

    LaunchedEffect(key1 = Unit) {
        networkChecker.isConnected.collect { connected ->
            if (previousState == null) {
                previousState = connected

                if (!connected) {
                    snackbarJob?.cancel()
                    snackbarJob = launch {
                        snackbarHostState.showSnackbar(
                            message = noConnection,
                            duration = SnackbarDuration.Long
                        )
                    }
                }

                return@collect
            }

            if (previousState != connected) {
                if (previousState == false) {
                    retryManager.retry()
                }

                snackbarJob?.cancel()
                snackbarHostState.currentSnackbarData?.dismiss()

                snackbarJob = launch {
                    snackbarHostState.showSnackbar(
                        message = if (connected) connectionRestored else noConnection,
                        duration = if (connected) SnackbarDuration.Short else SnackbarDuration.Long
                    )
                }
            }

            previousState = connected
        }
    }
}