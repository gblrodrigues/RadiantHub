package com.gblrod.radianthub.ui.features.agents.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.gblrod.radianthub.ui.theme.BackgroundOne
import com.gblrod.radianthub.ui.theme.BackgroundThree

@Composable
fun AgentContentCard(
    icon: String?,
    iconContentDescription: String?,
    title: String,
    description: String,
    tintIcon: Boolean = false
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(
            topStart = 0.dp,
            topEnd = 30.dp,
            bottomStart = 30.dp,
            bottomEnd = 30.dp
        ),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 6.dp),
        border = BorderStroke(width = 1.dp, color = BackgroundOne.copy(alpha = 0.8f)),
        colors = CardDefaults.cardColors(containerColor = BackgroundThree)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            AsyncImage(
                model = icon,
                contentDescription = iconContentDescription,
                modifier = Modifier
                    .size(45.dp)
                    .clip(CircleShape),
                colorFilter = if (tintIcon) {
                    ColorFilter.tint(color = MaterialTheme.colorScheme.onSurface)
                } else {
                    null
                }
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Start,
                )
            }
        }
    }
}