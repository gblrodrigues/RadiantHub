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

    navigate(route) {
        launchSingleTop = true
        restoreState = true

        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
    }
}

fun NavHostController.navigateFromSearch(
    targetRoute: String,
    targetBaseRoute: String
) {
    val previousEntry = previousBackStackEntry
    val previousRoute = previousEntry
        ?.destination
        ?.route
        ?.substringBefore(delimiter = "?")

    navigate(targetRoute) {
        if (previousEntry != null && previousRoute == targetBaseRoute) {
            popUpTo(previousEntry.destination.id) {
                inclusive = true
            }
        } else {
            popUpTo(Routes.Search.ROUTE) {
                inclusive = true
            }
        }
    }
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

    popBackStack(
        route = Routes.Search.ROUTE,
        inclusive = true
    )

    if (previousRoute == targetRoute) {
        currentBackStackEntry
            ?.savedStateHandle
            ?.set(
                key = selectionKey,
                value = selectionValue
            )

        return
    }

    navigateToBottomBar(route = targetRoute)

    currentBackStackEntry
        ?.savedStateHandle
        ?.set(
            key = selectionKey,
            value = selectionValue
        )
}