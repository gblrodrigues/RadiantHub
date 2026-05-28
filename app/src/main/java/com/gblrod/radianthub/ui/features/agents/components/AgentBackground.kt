package com.gblrod.radianthub.ui.features.agents.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage

@Composable
fun AgentBackground(
    agentBackground: String?,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = agentBackground,
        contentDescription = null,
        modifier = modifier,
        contentScale = ContentScale.Crop,
        alpha = 0.25f
    )
}