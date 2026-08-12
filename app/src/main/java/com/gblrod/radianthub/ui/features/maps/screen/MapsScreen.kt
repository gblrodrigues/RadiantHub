package com.gblrod.radianthub.ui.features.maps.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.gblrod.radianthub.ui.features.maps.components.MapPage
import com.gblrod.radianthub.ui.features.maps.state.MapsUiState
import com.gblrod.radianthub.ui.features.maps.viewmodel.MapsViewModel
import com.gblrod.radianthub.ui.shared.components.ErrorMessage
import com.gblrod.radianthub.ui.shared.components.LoadingScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun MapsScreen(
    mapsViewModel: MapsViewModel = koinViewModel(),
    initialMapUuid: String?
) {
    val uiState by mapsViewModel.mapsState.collectAsState()

    val mapCount = when (val state = uiState) {
        is MapsUiState.Success -> state.maps.size
        else -> 0
    }

    val pagerState = rememberPagerState(
        pageCount = { mapCount }
    )


    when(val state = uiState) {
        is MapsUiState.Loading -> {
            LoadingScreen()
        }

        is MapsUiState.Error -> {
            val message = if (state.code == null) {
                stringResource(id = state.messageResId)
            } else {
                stringResource(id = state.messageResId, state.code)
            }

            ErrorMessage(
                message = message,
                onRetry = { mapsViewModel.retry() }
            )
        }

        is MapsUiState.Success -> {
            LaunchedEffect(initialMapUuid) {
                initialMapUuid?.let { uuid ->
                    val index = state.maps.indexOfFirst {
                        it.uuid == uuid
                    }

                    if (index >= 0) {
                        pagerState.animateScrollToPage(index)
                    }

                    mapsViewModel.clearSelectedMap()
                }
            }

            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                MapPage(
                    maps = state.maps[page],
                    currentPage = pagerState.currentPage,
                    pageCount = state.maps.size
                )
            }
        }
    }
}