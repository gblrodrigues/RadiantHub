package com.gblrod.radianthub.domain.agents.model

data class Agent(
    val name: String,
    val description: String,
    val icon: String?,
    val portrait: String?,
    val background: String?,
    val role: Role?,
    val abilities: List<Ability>,
    val uuid: String
)