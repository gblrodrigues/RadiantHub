package com.gblrod.radianthub.ui.features.favorites.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CardMembership
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.gblrod.radianthub.R
import com.gblrod.radianthub.data.room.model.FavoriteFilter
import com.gblrod.radianthub.ui.theme.NavigationSelected

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
                icon = {
                    when (filter) {
                        FavoriteFilter.AGENT -> {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = null,
                                tint = if (selectedFilter == filter) NavigationSelected else Color.LightGray
                            )
                        }

                        FavoriteFilter.CARD -> {
                            Icon(
                                imageVector = Icons.Default.CardMembership,
                                contentDescription = null,
                                tint = if (selectedFilter == filter) NavigationSelected else Color.LightGray
                            )
                        }

                        else -> {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = null,
                                tint = if (selectedFilter == filter) NavigationSelected else Color.LightGray
                            )
                        }
                    }
                },
                selected = selectedFilter == filter,
                onClick = { onFilterSelected(filter) },
                shape = SegmentedButtonDefaults.itemShape(
                    index = index,
                    count = filters.size,
                    baseShape = RoundedCornerShape(16.dp)
                ),
                colors = SegmentedButtonDefaults.colors(
                    activeContainerColor = NavigationSelected.copy(alpha = 0.25f),
                    activeContentColor = Color.White,
                    activeBorderColor = Color.White,

                    inactiveContainerColor = Color.Transparent,
                    inactiveContentColor = Color.LightGray,
                    inactiveBorderColor = Color.White.copy(alpha = 0.20f),

                    disabledInactiveBorderColor = Color.White.copy(alpha = 0.08f),
                    disabledInactiveContentColor = Color.White.copy(alpha = 0.30f)
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