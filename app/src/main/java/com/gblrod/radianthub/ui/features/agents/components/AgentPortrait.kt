package com.gblrod.radianthub.ui.features.agents.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage

@Composable
fun AgentPortrait(
    agentPortrait: String?,
    name: String,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = agentPortrait,
        contentDescription = name,
        modifier = modifier.zIndex(1f),
        contentScale = ContentScale.Fit
    )
}