package com.gblrod.radianthub.ui.shared.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

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
            FavoriteButton(
                isFavorite = isFavorite,
                onClick = { onFavoriteClick() }
            )
        }
    }
}