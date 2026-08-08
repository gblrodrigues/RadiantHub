package com.gblrod.radianthub.ui.features.home.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.gblrod.radianthub.R
import com.gblrod.radianthub.navigation.Routes
import com.gblrod.radianthub.navigation.extensions.navigateToBottomBar
import com.gblrod.radianthub.ui.features.home.components.HomeItem

@Composable
fun HomeScreen(
    navHostController: NavHostController
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            HomeItem(
                title = stringResource(id = R.string.home_agents_title),
                subtitle = stringResource(id = R.string.home_agents_subtitle),
                background = painterResource(id = R.drawable.agents_background),
                onClick = { navHostController.navigateToBottomBar(route = Routes.Agents.ROUTE) }
            )
        }

        item {
            HomeItem(
                title = stringResource(id = R.string.home_maps_title),
                subtitle = stringResource(id = R.string.home_maps_subtitle),
                background = painterResource(id = R.drawable.maps_background),
                onClick = { navHostController.navigateToBottomBar(route = Routes.Maps.ROUTE) }
            )
        }

        item {
            HomeItem(
                title = stringResource(id = R.string.home_cards_title),
                subtitle = stringResource(id = R.string.home_cards_subtitle),
                background = painterResource(id = R.drawable.cards_background),
                onClick = { navHostController.navigate(route = Routes.Cards.ROUTE) }
            )
        }

        item {
            HomeItem(
                title = stringResource(id = R.string.home_tiers_title),
                subtitle = stringResource(id = R.string.home_tiers_subtitle),
                background = painterResource(id = R.drawable.tiers_background),
                onClick = { navHostController.navigate(route = Routes.Tiers.ROUTE) }
            )
        }

        item {
            HomeItem(
                title = stringResource(id = R.string.home_favorites_title),
                subtitle = stringResource(id = R.string.home_favorites_subtitle),
                background = painterResource(id = R.drawable.favorites_background),
                onClick = { navHostController.navigateToBottomBar(route = Routes.Favorites.ROUTE) }
            )
        }
    }
}