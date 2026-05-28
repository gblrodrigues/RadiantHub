package com.gblrod.radianthub.ui.features.maps.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.gblrod.radianthub.ui.features.maps.components.MapPage
import com.gblrod.radianthub.ui.features.maps.state.MapsUiState
import com.gblrod.radianthub.ui.features.maps.viewmodel.MapsViewModel
import com.gblrod.radianthub.ui.shared.components.ErrorMessage
import com.gblrod.radianthub.ui.shared.components.LoadingScreen

@Composable
fun MapsScreen(
    mapsViewModel: MapsViewModel
) {
    val uiState by mapsViewModel.mapsState.collectAsState()

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
            val pagerState = rememberPagerState { state.maps.size }

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