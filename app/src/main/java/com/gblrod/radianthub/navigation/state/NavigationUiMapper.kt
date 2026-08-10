package com.gblrod.radianthub.navigation.state

import com.gblrod.radianthub.R
import com.gblrod.radianthub.navigation.Routes
import com.gblrod.radianthub.navigation.bottomBarRoutes

fun mapRouteToNavigationUiState(
    route: String?,
): NavigationUiState {
    val isBottomBarScreen = route in bottomBarRoutes

    if (route == Routes.Search.ROUTE) {
        return NavigationUiState(
            titleRes = R.string.topbar_principal_title,
            showTopBar = false,
            showBottomBar = false,
            showDrawerIcon = false,
            showBackButton = false
        )
    }

    val titleRes = when (route) {
        Routes.Home.ROUTE -> R.string.topbar_home_title
        Routes.Maps.ROUTE_WITH_ARGUMENT -> R.string.topbar_maps_title
        Routes.Tiers.ROUTE_WITH_ARGUMENT -> R.string.topbar_tiers_title
        Routes.Cards.ROUTE_WITH_ARGUMENT -> R.string.topbar_cards_title
        Routes.Agents.ROUTE_WITH_ARGUMENT -> R.string.topbar_agents_title
        Routes.Favorites.ROUTE -> R.string.topbar_favorites_title
        else -> R.string.topbar_principal_title
    }

    return NavigationUiState(
        titleRes = titleRes,
        showTopBar = route != null,
        showBottomBar = isBottomBarScreen,
        showDrawerIcon = isBottomBarScreen,
        showBackButton = !isBottomBarScreen
    )
}