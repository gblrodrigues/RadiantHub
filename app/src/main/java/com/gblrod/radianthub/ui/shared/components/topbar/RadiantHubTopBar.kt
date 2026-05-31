package com.gblrod.radianthub.ui.shared.components.topbar

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.gblrod.radianthub.navigation.Routes
import com.gblrod.radianthub.navigation.state.NavigationUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RadiantHubTopBar(
    onOpenDrawer: () -> Unit,
    navHostController: NavHostController,
    navigationUiState: NavigationUiState
) {
    TopAppBar(
        modifier = Modifier.height(76.dp),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        ),
        title = {
            Text(
                text = stringResource(id = navigationUiState.titleRes),
                color = MaterialTheme.colorScheme.onSurface
            )
        },
        navigationIcon = {
            when {
                navigationUiState.showBackButton -> {
                    IconButton(
                        onClick = {
                            if (navHostController.previousBackStackEntry != null) {
                                navHostController.popBackStack()
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }

                navigationUiState.showDrawerIcon -> {
                    IconButton(
                        onClick = { onOpenDrawer() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }
            }
        },
        actions = {
            IconButton(
                onClick = { navHostController.navigate(route = Routes.Search.route) }
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.size(28.dp)
                )
            }
        }
    )
}