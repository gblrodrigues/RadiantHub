package com.gblrod.radianthub.ui.features.agents.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import com.gblrod.radianthub.domain.agents.model.Agent
import com.gblrod.radianthub.ui.shared.components.PagerIndicator

@Composable
fun AgentPage(
    agent: Agent,
    onViewSkills: (Agent) -> Unit,
    currentPage: Int,
    pageCount: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .animateContentSize()
    ) {
        AgentBackground(
            agentBackground = agent.background,
            modifier = Modifier
                .fillMaxSize()
                .offset(y = (-20).dp)
                .blur(radius = 80.dp)
                .scale(1.2f),
        )

        AgentPortrait(
            agentPortrait = agent.portrait,
            name = agent.name,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxHeight(0.78f)
                .offset(y = (-120).dp)
        )

        Column(
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            AgentInfoCard(
                agent = agent,
                onViewSkills = { onViewSkills(agent) }
            )

            PagerIndicator(
                currentPage = currentPage,
                pageCount = pageCount
            )
        }
    }
}