package com.gblrod.radianthub.ui.shared.components.bottombar

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.gblrod.radianthub.R
import com.gblrod.radianthub.navigation.Routes
import com.gblrod.radianthub.navigation.extensions.isNavigationSection
import com.gblrod.radianthub.navigation.extensions.navigateToBottomBar
import com.gblrod.radianthub.ui.shared.model.NavigationItem
import com.gblrod.radianthub.ui.theme.NavigationSelected

@Composable
fun RadiantHubBottomBar(
    navHostController: NavHostController
) {
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val items = listOf(
        NavigationItem(
            label = stringResource(id = R.string.bottom_bar_home_title),
            icon = Icons.Default.Home,
            route = Routes.Home.ROUTE
        ),
        NavigationItem(
            label = stringResource(id = R.string.bottom_bar_agents_title),
            icon = Icons.Default.Groups,
            route = Routes.Agents.ROUTE
        ),
        NavigationItem(
            label = stringResource(id = R.string.bottom_bar_maps_title),
            icon = Icons.Default.Map,
            route = Routes.Maps.ROUTE
        ),
        NavigationItem(
            label = stringResource(id = R.string.bottom_bar_favorites_title),
            icon = Icons.Default.Star,
            route = Routes.Favorites.ROUTE
        )
    )
    NavigationBar(
        modifier = Modifier
            .navigationBarsPadding()
            .height(62.dp)
            .border(
                width = 0.5.dp,
                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.15f),
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
            ),
        containerColor = Color.Transparent
    ) {
        items.forEach { item ->
            NavigationBarItem(
                selected = isNavigationSection(
                    currentRoute = currentDestination?.route,
                    sectionRoute = item.route
                ),
                label = {
                    Text(
                        text = item.label
                    )
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label
                    )
                },
                onClick = {
                    navHostController.navigateToBottomBar(route = item.route)
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = NavigationSelected,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurface,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurface,
                    selectedTextColor = NavigationSelected,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}