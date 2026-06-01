package com.gblrod.radianthub.ui.features.favorites.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.gblrod.radianthub.R
import com.gblrod.radianthub.data.room.model.FavoriteFilter
import com.gblrod.radianthub.ui.theme.GreenActions

@Composable
fun FavoriteFilter(
    selectedFilter: FavoriteFilter,
    onFilterSelected: (FavoriteFilter) -> Unit,
    agentCount: Int,
    cardCount: Int,
    modifier: Modifier = Modifier
) {
    val filters = FavoriteFilter.entries

    SingleChoiceSegmentedButtonRow(
        modifier = modifier.fillMaxWidth()
    ) {
        filters.forEachIndexed { index, filter ->
            SegmentedButton(
                enabled = when (filter) {
                    FavoriteFilter.ALL -> true
                    FavoriteFilter.CARD -> cardCount > 0
                    FavoriteFilter.AGENT -> agentCount > 0
                },
                selected = selectedFilter == filter,
                onClick = { onFilterSelected(filter) },
                shape = SegmentedButtonDefaults.itemShape(
                    index = index,
                    count = filters.size
                ),
                colors = SegmentedButtonDefaults.colors(
                    activeContainerColor = GreenActions,
                    activeContentColor = Color.White,
                    inactiveContainerColor = Color.Transparent,
                    inactiveContentColor = MaterialTheme.colorScheme.onSurface,
                    disabledInactiveContentColor = MaterialTheme.colorScheme.surfaceVariant
                        .copy(alpha = 0.3f),
                    disabledInactiveContainerColor = MaterialTheme.colorScheme.onSurface
                        .copy(alpha = 0.4f),
                )
            ) {
                Text(
                    text = when (filter) {
                        FavoriteFilter.ALL -> {
                            stringResource(
                                id = R.string.button_favorite_filter_all,
                                cardCount + agentCount
                            )
                        }

                        FavoriteFilter.AGENT -> {
                            stringResource(
                                id = R.string.button_favorite_filter_agents,
                                agentCount
                            )
                        }

                        FavoriteFilter.CARD -> {
                            stringResource(
                                id = R.string.button_favorite_filter_cards,
                                cardCount
                            )
                        }
                    }
                )
            }
        }
    }
}