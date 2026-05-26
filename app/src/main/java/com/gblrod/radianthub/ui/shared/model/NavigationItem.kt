package com.gblrod.radianthub.ui.shared.model

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val label: String,
    val icon: ImageVector,
    val route: String
)
