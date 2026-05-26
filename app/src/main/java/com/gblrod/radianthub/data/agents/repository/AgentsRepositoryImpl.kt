package com.gblrod.radianthub.data.agents.repository

import com.gblrod.radianthub.data.agents.mapper.toDomain
import com.gblrod.radianthub.data.agents.remote.api.AgentsApi
import com.gblrod.radianthub.domain.agents.model.Agent
import com.gblrod.radianthub.domain.agents.repository.AgentsRepository

class AgentsRepositoryImpl(
    private val api: AgentsApi
) : AgentsRepository {
    override suspend fun getAgents(): List<Agent> {
        return api
            .getAgents()
            .data
            .map { it.toDomain() }
    }
}