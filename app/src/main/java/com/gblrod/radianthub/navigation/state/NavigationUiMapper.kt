package com.gblrod.radianthub.navigation.state

import com.gblrod.radianthub.R
import com.gblrod.radianthub.navigation.Routes
import com.gblrod.radianthub.navigation.extensions.isNavigationSection

fun mapRouteToNavigationUiState(
    route: String?,
): NavigationUiState {
    if (route == null) {
        return NavigationUiState(
            titleRes = R.string.topbar_principal_title,
            showTopBar = false,
            showBottomBar = false,
            showDrawerIcon = false,
            showBackButton = false
        )
    }

    val currentBaseRoute = route.substringBefore(delimiter = "?")

    if (currentBaseRoute == Routes.Search.ROUTE) {
        return NavigationUiState(
            titleRes = R.string.topbar_principal_title,
            showTopBar = false,
            showBottomBar = false,
            showDrawerIcon = false,
            showBackButton = false
        )
    }

    val isHome = isNavigationSection(
        currentRoute = route,
        sectionRoute = Routes.Home.ROUTE
    )

    val isAgents = isNavigationSection(
        currentRoute = route,
        sectionRoute = Routes.Agents.ROUTE
    )

    val isMaps = isNavigationSection(
        currentRoute = route,
        sectionRoute = Routes.Maps.ROUTE
    )

    val isFavorites = isNavigationSection(
        currentRoute = route,
        sectionRoute = Routes.Favorites.ROUTE
    )

    val isBottomBarScreen = isHome || isAgents || isMaps || isFavorites

    val titleRes = when {
        isHome -> R.string.topbar_home_title
        isAgents -> R.string.topbar_agents_title
        isMaps -> R.string.topbar_maps_title
        isFavorites -> R.string.topbar_favorites_title
        currentBaseRoute == Routes.Tiers.ROUTE -> R.string.topbar_tiers_title
        currentBaseRoute == Routes.Cards.ROUTE -> R.string.topbar_cards_title
        else -> R.string.topbar_principal_title
    }

    return NavigationUiState(
        titleRes = titleRes,
        showTopBar = true,
        showBottomBar = isBottomBarScreen,
        showDrawerIcon = isBottomBarScreen,
        showBackButton = !isBottomBarScreen
    )
}