package com.gblrod.radianthub.navigation.state

import com.gblrod.radianthub.R
import com.gblrod.radianthub.navigation.Routes

fun mapRouteToNavigationUiState(
    route: String?,
): NavigationUiState {

    val principalScreens = setOf(
        Routes.Home.route,
        Routes.Agents.route,
        Routes.Maps.route,
        Routes.Favorites.route
    )

    val isPrincipalScreen = route in principalScreens
    val showBackButton = !isPrincipalScreen

    if (route == Routes.Search.route) {
        return NavigationUiState(
            titleRes = R.string.topbar_principal_title
        )
    }

    val titleRes = when (route) {
        Routes.Home.route -> R.string.topbar_home_title
        Routes.Agents.route -> R.string.topbar_agents_title
        Routes.Maps.route -> R.string.topbar_maps_title
        Routes.Favorites.route -> R.string.topbar_favorites_title
        else -> R.string.topbar_principal_title
    }

    return NavigationUiState(
        titleRes = titleRes
    )
}