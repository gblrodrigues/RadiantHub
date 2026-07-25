package com.gblrod.radianthub.ui.shared.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.gblrod.radianthub.ui.theme.RetryActions

@Composable
fun PagerIndicator(
    currentPage: Int,
    pageCount: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 40.dp),
        horizontalArrangement = Arrangement.spacedBy(
            space = 3.dp,
            alignment = Alignment.CenterHorizontally
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(times = pageCount) { page ->
            Dot(
                selected = page == currentPage
            )
        }
    }
}

@Composable
private fun Dot(
    selected: Boolean
) {
    val scale by animateFloatAsState(
        targetValue = if (selected) 1.1f else 0.55f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMediumLow
        ),
        label = "DotScale"
    )

    val alpha by animateFloatAsState(
        targetValue = if (selected) 1.1f else 0.55f,
        label = "DotAlpha"
    )

    val color by animateColorAsState(
        targetValue = if (selected) RetryActions else MaterialTheme.colorScheme.outline,
        label = "DotColor"
    )

    Box(
        modifier = Modifier
            .size(8.dp)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
                this.alpha = alpha
            }
            .background(
                color = color,
                shape = CircleShape
            )
    )
}