package com.gblrod.radianthub.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.gblrod.radianthub.navigation.Routes
import com.gblrod.radianthub.ui.features.home.screen.HomeScreen

fun NavGraphBuilder.homeRoute(
    navHostController: NavHostController
) {
    composable(route = Routes.Home.ROUTE) {
        HomeScreen(
            navHostController = navHostController
        )
    }
}