package com.gblrod.radianthub.ui.features.search.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.SelectAll
import androidx.compose.material.icons.filled.Style
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.ui.graphics.vector.ImageVector
import com.gblrod.radianthub.R

enum class SearchFilterType(
    val titleRes: Int,
    val icon: ImageVector
) {
    ALL(titleRes = R.string.search_filter_all, icon = Icons.Default.SelectAll),
    AGENTS(titleRes = R.string.search_filter_agents, icon = Icons.Default.Groups),
    MAPS(titleRes = R.string.search_filter_maps, icon = Icons.Default.Map),
    CARDS(titleRes = R.string.search_filter_cards, icon = Icons.Default.Style),
    TIERS(titleRes = R.string.search_filter_tiers, icon = Icons.Default.WorkspacePremium)
}