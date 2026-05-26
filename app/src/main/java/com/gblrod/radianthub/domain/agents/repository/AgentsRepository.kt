package com.gblrod.radianthub.domain.agents.repository

import com.gblrod.radianthub.domain.agents.model.Agent

interface AgentsRepository {
    suspend fun getAgents(): List<Agent>
}