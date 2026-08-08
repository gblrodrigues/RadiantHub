package com.gblrod.radianthub.navigation

object Routes {
    object Home {
        const val ROUTE = "home"
    }

    object Agents {
        const val ROUTE = "agents"
        const val ARGUMENT = "agentUuid"
        const val ROUTE_WITH_ARGUMENT = "$ROUTE?$ARGUMENT={$ARGUMENT}"

        fun createRoute(agentUuid: String): String {
            return "$ROUTE?$ARGUMENT=$agentUuid"
        }
    }

    object Maps {
        const val ROUTE = "maps"
        const val ARGUMENT = "mapUuid"
        const val ROUTE_WITH_ARGUMENT = "$ROUTE?$ARGUMENT={$ARGUMENT}"

        fun createRoute(mapUuid: String): String {
            return "$ROUTE?$ARGUMENT=$mapUuid"
        }
    }

    object Cards {
        const val ROUTE = "cards"
        const val ARGUMENT = "cardUuid"
        const val ROUTE_WITH_ARGUMENT = "$ROUTE?$ARGUMENT={$ARGUMENT}"

        fun createRoute(cardUuid: String): String {
            return "$ROUTE?$ARGUMENT=$cardUuid"
        }
    }

    object Tiers {
        const val ROUTE = "tiers"
        const val ARGUMENT = "tierId"
        const val ROUTE_WITH_ARGUMENT = "$ROUTE?$ARGUMENT={$ARGUMENT}"

        fun createRoute(tierId: Int?): String {
            return "$ROUTE?$ARGUMENT=$tierId"
        }
    }

    object Favorites {
        const val ROUTE = "favorites"
    }

    object Search {
        const val ROUTE = "search"
    }
}