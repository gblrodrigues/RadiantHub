package com.gblrod.radianthub.ui.shared.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gblrod.radianthub.ui.theme.YellowActions

@Composable
fun SectionHeader(
    name: String,
    isFavorite: Boolean? = null,
    onFavoriteClick: (() -> Unit)? = null
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = name.uppercase(),
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.displayMedium
        )

        if (isFavorite != null && onFavoriteClick != null) {
            IconButton(
                onClick = { onFavoriteClick() }
            ) {
                Icon(
                    imageVector = if (isFavorite) Icons.Default.Star else Icons.Default.StarBorder,
                    contentDescription = null,
                    tint = if (isFavorite) YellowActions else MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.size(50.dp)
                )
            }
        }
    }
}