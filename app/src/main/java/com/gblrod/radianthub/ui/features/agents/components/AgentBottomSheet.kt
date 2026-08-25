package com.gblrod.radianthub.ui.features.agents.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.gblrod.radianthub.R
import com.gblrod.radianthub.domain.agents.model.Agent
import com.gblrod.radianthub.ui.theme.BackgroundOne

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgentBottomSheet(
    agent: Agent,
    sheetState: SheetState,
    onDismiss: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = sheetState,
        containerColor = BackgroundOne
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                AgentBottomSheetSectionTitle(text = R.string.agent_description_title)
            }

            item {
                AgentContentCard(
                    icon = agent.icon,
                    iconContentDescription = agent.name,
                    title = agent.name,
                    description = agent.description
                )
            }

            item {
                HorizontalDivider(
                    thickness = 1.dp,
                    color = Color.White.copy(alpha = 0.12f)
                )
            }

            item {
                AgentBottomSheetSectionTitle(text = R.string.agent_skills_title)
            }

            items(agent.abilities) {
                AgentContentCard(
                    icon = it.icon,
                    iconContentDescription = it.name,
                    title = it.name,
                    description = it.description,
                    tintIcon = true
                )
            }
        }
    }
}