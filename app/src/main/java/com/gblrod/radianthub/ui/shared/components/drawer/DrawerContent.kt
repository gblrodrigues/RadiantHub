package com.gblrod.radianthub.ui.shared.components.drawer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.AsyncImage
import com.gblrod.radianthub.R
import com.gblrod.radianthub.navigation.Routes
import com.gblrod.radianthub.ui.shared.model.DrawerPreferenceItem
import com.gblrod.radianthub.ui.shared.model.NavigationItem
import com.gblrod.radianthub.ui.theme.ThemeOptions
import com.gblrod.radianthub.ui.theme.viewmodel.ThemeViewModel

@Composable
fun DrawerContent(
    navController: NavController,
    onItemClick: () -> Unit,
    themeViewModel: ThemeViewModel
) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    var showThemeDialog by remember { mutableStateOf(false) }

    val theme = themeViewModel.theme.collectAsState().value ?: ThemeOptions.SYSTEM

    val items = listOf(
        NavigationItem(
            label = stringResource(id = R.string.bottom_bar_home_title),
            icon = Icons.Default.Home,
            route = Routes.Home.route
        ),
        NavigationItem(
            label = stringResource(id = R.string.bottom_bar_agents_title),
            icon = Icons.Default.Person,
            route = Routes.Agents.route
        ),
        NavigationItem(
            label = stringResource(id = R.string.bottom_bar_maps_title),
            icon = Icons.Default.Map,
            route = Routes.Maps.route
        ),
        NavigationItem(
            label = stringResource(id = R.string.bottom_bar_favorites_title),
            icon = Icons.Default.Star,
            route = Routes.Favorites.route
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = R.drawable.logo,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(shape = CircleShape)
                )
                Spacer(modifier = Modifier.width(12.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = stringResource(id = R.string.drawer_title),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = stringResource(id = R.string.drawer_sub_title),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

        Spacer(modifier = Modifier.height(8.dp))

        items.forEach { item ->
            NavigationDrawerItem(
                label = {
                    Text(
                        text = item.label,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label,
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                },
                selected = currentRoute == item.route,
                onClick = {
                    onItemClick()
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                shape = RoundedCornerShape(16.dp)
            )
        }

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Text(
            text = stringResource(id = R.string.drawer_category_preference),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        DrawerPreferenceItem(
            title = stringResource(id = R.string.drawer_item_theme),
            label =  stringResource(id = theme.label),
            icon = Icons.Default.Palette,
            contentDescription = stringResource(id = R.string.drawer_item_themes_cd),
            onClick = { showThemeDialog = true }
        )

        if (showThemeDialog) {
            ThemeMenu(
                selectedTheme = theme,
                onThemeSelected = { theme ->
                    themeViewModel.setTheme(theme = theme)
                },
                onDismiss = { showThemeDialog = false }
            )
        }
    }
}