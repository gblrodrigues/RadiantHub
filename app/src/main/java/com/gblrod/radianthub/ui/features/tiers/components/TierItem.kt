package com.gblrod.radianthub.ui.features.tiers.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.gblrod.radianthub.domain.tiers.model.Tier
import com.gblrod.radianthub.ui.theme.PinkActions
import com.gblrod.radianthub.ui.theme.PurpleActions

@Composable
fun TierItem(
    tier: Tier,
    modifier: Modifier = Modifier,
    isHighlighted: Boolean = false
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
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
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = tier.icon,
                contentDescription = tier.tierName,
                modifier = Modifier.size(64.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = tier.tierName,
                style = MaterialTheme.typography.labelMedium,
                textAlign = TextAlign.Center
            )
        }
    }
}