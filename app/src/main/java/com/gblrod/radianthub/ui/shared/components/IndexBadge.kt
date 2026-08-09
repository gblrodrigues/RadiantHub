package com.gblrod.radianthub.ui.shared.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun IndexBadge(
    modifier: Modifier = Modifier,
    index: Int,
    total: Int
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(
            text = "${index + 1} / $total",
            modifier = Modifier.padding(horizontal = 9.dp, vertical = 4.dp),
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.labelSmall
        )
    }
}