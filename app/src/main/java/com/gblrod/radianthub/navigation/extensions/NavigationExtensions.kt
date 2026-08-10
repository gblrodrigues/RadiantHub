package com.gblrod.radianthub.navigation.extensions

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.gblrod.radianthub.navigation.Routes

fun NavHostController.navigateToBottomBar(route: String) {
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