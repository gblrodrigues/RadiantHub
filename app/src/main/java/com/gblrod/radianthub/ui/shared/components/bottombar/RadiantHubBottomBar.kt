package com.gblrod.radianthub.ui.shared.components.bottombar

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.gblrod.radianthub.navigation.extensions.isNavigationSection
import com.gblrod.radianthub.navigation.extensions.navigateToBottomBar
import com.gblrod.radianthub.ui.shared.model.NavigationItem
import com.gblrod.radianthub.ui.theme.BackgroundOne
import com.gblrod.radianthub.ui.theme.NavigationSelected

@Composable
fun RadiantHubBottomBar(
    navHostController: NavHostController
) {
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar(
        modifier = Modifier
            .clip(
                shape = RoundedCornerShape(
                    topStart = 16.dp,
                    topEnd = 16.dp
                )
            ),
        containerColor = BackgroundOne
    ) {
        NavigationItem.entries
            .filter { it.showInBottomBar }
            .forEach { item ->
                NavigationBarItem(
                    selected = isNavigationSection(
                        currentRoute = currentDestination?.route,
                        sectionRoute = item.route
                    ),
                    label = {
                        Text(
                            text = stringResource(id = item.label)
                        )
                    },
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = stringResource(id = item.label)
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