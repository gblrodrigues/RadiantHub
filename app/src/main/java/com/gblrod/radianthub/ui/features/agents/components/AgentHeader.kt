package com.gblrod.radianthub.ui.features.agents.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AgentHeader(
    name: String,
    onFavoriteClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = name.uppercase(),
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.displayMedium
        )

        IconButton(
            onClick = { onFavoriteClick() }
        ) {
            Icon(
                imageVector = Icons.Default.StarBorder,
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )
        }
    }
}