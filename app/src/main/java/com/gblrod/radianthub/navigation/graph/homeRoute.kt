package com.gblrod.radianthub.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gblrod.radianthub.navigation.Routes
import com.gblrod.radianthub.ui.features.home.screen.HomeScreen

fun NavGraphBuilder.homeRoute() {
    composable(route = Routes.Home.route) {
        HomeScreen()
    }
}