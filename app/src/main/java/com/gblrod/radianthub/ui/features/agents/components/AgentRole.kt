package com.gblrod.radianthub.ui.features.agents.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun AgentRole(
    roleName: String?,
    roleIcon: String?
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = roleIcon,
            contentDescription = null,
            modifier = Modifier.size(18.dp),
            colorFilter = ColorFilter.tint(
                color = MaterialTheme.colorScheme.onSurface
            )
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = roleName?.uppercase().orEmpty(),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}