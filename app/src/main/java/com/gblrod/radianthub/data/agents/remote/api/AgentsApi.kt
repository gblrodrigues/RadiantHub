package com.gblrod.radianthub.data.agents.remote.api

import com.gblrod.radianthub.data.agents.remote.dto.AgentResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface AgentsApi {
    @GET(value = "v1/agents")
    suspend fun getAgents(
        @Query("isPlayableCharacter")
        isPlayableCharacter: Boolean = true,

        @Query("language")
        language: String
    ): AgentResponseDto
}