package com.gblrod.radianthub.navigation

sealed class Routes(
    val route: String
) {
    object Home : Routes(
        route = "home"
    )

    object Agents : Routes(
        route = "agents"
    )

    object Maps : Routes(
        route = "maps"
    )

    object Favorites : Routes(
        route = "favorites"
    )

    object Search : Routes(
        route = "search"
    )

    object Cards : Routes(
        route = "cards"
    )
}