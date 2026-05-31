package com.gblrod.radianthub.ui.features.cards.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.gblrod.radianthub.domain.cards.model.Card
import com.gblrod.radianthub.ui.features.cards.components.CardItem
import com.gblrod.radianthub.ui.features.cards.state.CardsUiState
import com.gblrod.radianthub.ui.features.cards.viewmodel.CardsViewModel
import com.gblrod.radianthub.ui.shared.components.ErrorMessage
import com.gblrod.radianthub.ui.shared.components.LoadingScreen

@Composable
fun CardsScreen(
    cardsViewModel: CardsViewModel
) {
    val uiState by cardsViewModel.cardsState.collectAsState()
    var selectedCard by remember { mutableStateOf<Card?>(null) }

    when (val state = uiState) {
        is CardsUiState.Loading -> {
            LoadingScreen()
        }

        is CardsUiState.Error -> {
            val message = if (state.code == null) {
                stringResource(id = state.messageResId)
            } else {
                stringResource(id = state.messageResId, state.code)
            }

            ErrorMessage(
                message = message,
                onRetry = { cardsViewModel.retry() }
            )
        }

        is CardsUiState.Success -> {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(
                    items = state.cards,
                    key = { it.uuid }
                ) { card ->
                    val isFavorite by cardsViewModel
                        .isFavorite(card.uuid)
                        .collectAsState(initial = false)

                    CardItem(
                        card = card,
                        onFavoriteClick = { cardsViewModel.toggleFavorite(card) },
                        modifier = Modifier.animateItem(),
                        isFavorite = isFavorite,
                        onClick = { selectedCard = card }
                    )
                }
            }
        }
    }
}