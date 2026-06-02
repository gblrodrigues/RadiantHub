package com.gblrod.radianthub.ui.features.cards.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.gblrod.radianthub.domain.cards.model.Card
import com.gblrod.radianthub.ui.shared.components.FavoriteButton
import com.gblrod.radianthub.ui.theme.PinkActions
import com.gblrod.radianthub.ui.theme.PurpleActions

@Composable
fun CardItem(
    card: Card,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    isFavorite: Boolean? = null,
    onFavoriteClick: (() -> Unit)? = null,
    isHighlighted: Boolean = false
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .border(
                width = if (isHighlighted) 3.dp else 0.dp,
                brush = Brush.verticalGradient(
                    colors = listOf(
                        PinkActions,
                        PurpleActions
                    )
                ),
                shape = RoundedCornerShape(16.dp)
            ),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Box {
            AsyncImage(
                model = card.icon,
                contentDescription = card.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            )

            if (isFavorite != null && onFavoriteClick != null) {
                FavoriteButton(
                    isFavorite = isFavorite,
                    onClick = { onFavoriteClick() },
                    unselectedTint = Color.White
                )
            }

            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.7f)
                            )
                        )
                    )
            ) {
                Text(
                    text = card.name,
                    modifier = Modifier.padding(12.dp),
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 2
                )
            }
        }
    }
}