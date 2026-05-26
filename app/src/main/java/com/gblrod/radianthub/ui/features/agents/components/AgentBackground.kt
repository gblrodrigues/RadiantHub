package com.gblrod.radianthub.ui.features.agents.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
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