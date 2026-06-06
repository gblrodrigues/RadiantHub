package com.gblrod.radianthub.ui.features.tiers.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.gblrod.radianthub.ui.features.tiers.components.TierSection
import com.gblrod.radianthub.ui.features.tiers.state.TiersUiState
import com.gblrod.radianthub.ui.features.tiers.viewmodel.TiersViewModel
import com.gblrod.radianthub.ui.shared.components.ErrorMessage
import com.gblrod.radianthub.ui.shared.components.LoadingScreen
import kotlinx.coroutines.delay

@Composable
fun TiersScreen(
    tiersViewModel: TiersViewModel
) {
    val uiState by tiersViewModel.tiersState.collectAsState()
    val initialTierUuid by tiersViewModel.initialTierUuid.collectAsState()
    val listState = rememberLazyListState()

    var highlightedTier by remember { mutableStateOf<Int?>(null) }

    when (val state = uiState) {
        is TiersUiState.Loading -> {
            LoadingScreen()
        }

        is TiersUiState.Error -> {
            val message = if (state.code == null) {
                stringResource(id = state.messageResId)
            } else {
                stringResource(id = state.messageResId, state.code)
            }

            ErrorMessage(
                message = message,
                onRetry = { tiersViewModel.retry() }
            )
        }

        is TiersUiState.Success -> {
            LaunchedEffect(initialTierUuid) {
                initialTierUuid?.let { tierId ->

                    highlightedTier = tierId

                    val groupIndex =
                        state.tiers.indexOfFirst { group ->
                            group.tiers.any { it.tier == tierId }
                        }

                    if (groupIndex >= 0) {
                        listState.animateScrollToItem(groupIndex)
                    }

                    delay(5000)

                    highlightedTier = null
                    tiersViewModel.clearSelectedTier()
                }
            }

            LazyColumn(
                state = listState,
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                items(
                    items = state.tiers,
                    key = { it.rankName }) { group ->
                    TierSection(
                        group = group,
                        highlightedTier = highlightedTier
                    )
                }
            }
        }
    }
}