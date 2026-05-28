package com.gblrod.radianthub.ui.features.favorites.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.gblrod.radianthub.domain.agents.model.Agent
import com.gblrod.radianthub.ui.features.agents.components.AgentBottomSheet
import com.gblrod.radianthub.ui.features.agents.viewmodel.AgentsViewModel
import com.gblrod.radianthub.ui.features.favorites.components.EmptyFavorites
import com.gblrod.radianthub.ui.features.favorites.components.FavoriteCard
import com.gblrod.radianthub.ui.features.favorites.state.FavoritesUiState
import com.gblrod.radianthub.ui.features.favorites.viewmodel.FavoritesViewModel
import com.gblrod.radianthub.ui.shared.components.ErrorMessage
import com.gblrod.radianthub.ui.shared.components.LoadingScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    favoritesViewModel: FavoritesViewModel,
    agentsViewModel: AgentsViewModel,
    onNavigateAgents: () -> Unit
) {
    val uiState by favoritesViewModel.favoritesState.collectAsState()

    var selectedAgent by remember { mutableStateOf<Agent?>(null) }
    val sheetState = rememberModalBottomSheetState()

    when (val state = uiState) {

        is FavoritesUiState.Loading -> {
            LoadingScreen()
        }

        is FavoritesUiState.Error -> {
            val message = if (state.code == null) {
                stringResource(id = state.messageResId)
            } else {
                stringResource(
                    id = state.messageResId,
                    state.code
                )
            }

            ErrorMessage(
                message = message,
                onRetry = { favoritesViewModel.retry() }
            )
        }

        is FavoritesUiState.Success -> {
            if (state.favorites.isEmpty()) {
                EmptyFavorites(
                    onNavigateAgents = { onNavigateAgents() }
                )

            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(
                        items = state.favorites,
                        key = { it.uuid }
                    ) { favorite ->
                        FavoriteCard(
                            item = favorite,
                            onClick = {
                                selectedAgent = agentsViewModel.getAgentByUuid(favorite.uuid)
                            },
                            onFavoriteClick = { favoritesViewModel.toggleFavorite(favorite) }
                        )
                    }
                }
            }

            selectedAgent?.let { selected ->
                AgentBottomSheet(
                    agent = selected,
                    sheetState = sheetState,
                    onDismiss = { selectedAgent = null }
                )
            }
        }
    }
}