package com.gblrod.radianthub.data.agents.remote.dto

data class AgentDto(
    val displayName: String,
    val description: String,
    val displayIcon: String,
    val background: String,
    val fullPortrait: String,
    val role: RoleDto,
    val abilities: List<AbilityDto>,
    val uuid: String
)