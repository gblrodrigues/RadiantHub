package com.gblrod.radianthub.navigation.state

import com.gblrod.radianthub.R
import com.gblrod.radianthub.navigation.Routes

fun mapRouteToNavigationUiState(
    route: String?,
): NavigationUiState {

    val bottomBarScreens = setOf(
        Routes.Home.route,
        Routes.Agents.route,
        Routes.Maps.route,
        Routes.Favorites.route
    )

    val isBottomBarScreen = route in bottomBarScreens

    if (route == Routes.Search.route) {
        return NavigationUiState(
            titleRes = R.string.topbar_principal_title,
            showTopBar = false,
            showBottomBar = false,
            showDrawerIcon = false,
            showBackButton = false
        )
    }

    val titleRes = when (route) {
        Routes.Home.route -> R.string.topbar_home_title
        Routes.Maps.route -> R.string.topbar_maps_title
        Routes.Cards.route -> R.string.topbar_cards_title
        Routes.Agents.route -> R.string.topbar_agents_title
        Routes.Favorites.route -> R.string.topbar_favorites_title
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