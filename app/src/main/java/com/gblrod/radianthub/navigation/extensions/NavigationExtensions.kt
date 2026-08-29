package com.gblrod.radianthub.navigation.extensions

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.gblrod.radianthub.navigation.Routes

fun NavHostController.navigateToBottomBar(route: String) {
    if (
        isNavigationSection(
            currentRoute = currentDestination?.route,
            sectionRoute = route
        )
    ) {
        return
    }

    val searchEntry = runCatching {
        getBackStackEntry(Routes.Search.ROUTE)
    }.getOrNull()

    if (searchEntry != null) {
        popBackStack(
            route = Routes.Search.ROUTE,
            inclusive = true
        )
    }

    navigate(route) {
        launchSingleTop = true
        restoreState = true

        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
    }
}

fun NavHostController.navigateFromSearch(
    targetRoute: String
) {
    navigate(targetRoute)
}

fun isNavigationSection(
    currentRoute: String?,
    sectionRoute: String
): Boolean {
    return currentRoute?.substringBefore(delimiter = "?") == sectionRoute
}

fun NavHostController.navigateToTopLevelFromSearch(
    targetRoute: String,
    selectionKey: String,
    selectionValue: String
) {
    val previousEntry = previousBackStackEntry ?: return
    val previousRoute = previousEntry
        .destination
        .route
        ?.substringBefore(delimiter = "?")

    if (previousRoute == targetRoute) {
        popBackStack()

        currentBackStackEntry
            ?.savedStateHandle
            ?.set(
                key = selectionKey,
                value = selectionValue
            )

        return
    }

    navigate(route = targetRoute)

    currentBackStackEntry
        ?.savedStateHandle
        ?.set(
            key = selectionKey,
            value = selectionValue
        )
}