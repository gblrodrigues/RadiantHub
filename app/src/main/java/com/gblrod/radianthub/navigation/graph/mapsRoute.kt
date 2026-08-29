package com.gblrod.radianthub.navigation.graph

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gblrod.radianthub.navigation.NavigationKeys.SELECTED_MAP_UUID
import com.gblrod.radianthub.navigation.Routes
import com.gblrod.radianthub.ui.features.maps.screen.MapsScreen
import com.gblrod.radianthub.ui.features.maps.viewmodel.MapsViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.mapsRoute() {
    composable(
        route = Routes.Maps.ROUTE
    ) { backStackEntry ->
        val selectedMapUuid by backStackEntry
            .savedStateHandle
            .getStateFlow<String?>(
                key = SELECTED_MAP_UUID,
                initialValue = null
            )
            .collectAsState()

        val mapsViewModel: MapsViewModel = koinViewModel()

        LaunchedEffect(selectedMapUuid) {
            selectedMapUuid?.let { uuid ->
                mapsViewModel.selectMap(uuid)

                backStackEntry.savedStateHandle[
                    SELECTED_MAP_UUID
                ] = null
            }
        }

        MapsScreen()
    }
}