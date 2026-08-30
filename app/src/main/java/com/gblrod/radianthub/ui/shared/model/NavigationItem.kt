package com.gblrod.radianthub.ui.shared.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Style
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.ui.graphics.vector.ImageVector
import com.gblrod.radianthub.R
import com.gblrod.radianthub.navigation.Routes

enum class NavigationItem(
    val label: Int,
    val icon: ImageVector,
    val route: String,
    val showInBottomBar: Boolean = true
) {
    HOME(
        label = R.string.drawer_item_home,
        icon = Icons.Default.Home,
        route = Routes.Home.ROUTE
    ),
    AGENTS(
        label = R.string.drawer_item_agents,
        icon = Icons.Default.Groups,
        route = Routes.Agents.ROUTE
    ),
    MAPS(
        label = R.string.drawer_item_maps,
        icon = Icons.Default.Map,
        route = Routes.Maps.ROUTE
    ),
    CARDS(
        label = R.string.drawer_item_cards,
        icon = Icons.Default.Style,
        route = Routes.Cards.ROUTE,
        showInBottomBar = false
    ),
    TIERS(
        label = R.string.drawer_item_tiers,
        icon = Icons.Default.WorkspacePremium,
        route = Routes.Tiers.ROUTE,
        showInBottomBar = false
    ),
    FAVORITES(
        label = R.string.drawer_item_favorites,
        icon = Icons.Default.Star,
        route = Routes.Favorites.ROUTE
    )
}