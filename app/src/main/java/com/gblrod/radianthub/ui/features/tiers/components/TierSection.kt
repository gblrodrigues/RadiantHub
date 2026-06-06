package com.gblrod.radianthub.ui.features.tiers.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.gblrod.radianthub.ui.features.tiers.model.TierGroup

@Composable
fun TierSection(
    group: TierGroup,
    highlightedTier: Int?
) {
    Column {
        Text(
            text = group.rankName,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Black,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            group.tiers.forEach { tier ->
                TierItem(
                    tier = tier,
                    modifier = Modifier.weight(1f),
                    isHighlighted = tier.tier == highlightedTier
                )
            }
        }
    }
}