package com.gblrod.radianthub.navigation.state

data class NavigationUiState(
    val titleRes: Int,
    val showTopBar: Boolean,
    val showBackButton: Boolean,
    val showBottomBar: Boolean,
    val showDrawerIcon: Boolean
)