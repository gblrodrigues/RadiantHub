package com.gblrod.radianthub.ui.features.agents.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.gblrod.radianthub.domain.agents.model.Agent
import com.gblrod.radianthub.ui.theme.NavigationSelected

@Composable
fun AgentInfoCard(
    agent: Agent,
    onViewSkills: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = 24.dp,
                vertical = 32.dp
            ),
        shape = RoundedCornerShape(32.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        border = BorderStroke(width = 1.dp, color = NavigationSelected)
    ) {
        Column(
            modifier = Modifier.padding(
                vertical = 24.dp,
                horizontal = 16.dp
            )
        ) {
            AgentHeader(
                name = agent.name,
                onFavoriteClick = {}
            )

            Spacer(modifier = Modifier.height(4.dp))

            AgentRole(
                roleIcon = agent.role?.icon,
                roleName = agent.role?.name
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = agent.description,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyLarge,
                overflow = TextOverflow.Ellipsis,
                maxLines = 5
            )

            Spacer(modifier = Modifier.height(12.dp))

            AgentActions(
                onViewSkills = onViewSkills
            )
        }
    }
}