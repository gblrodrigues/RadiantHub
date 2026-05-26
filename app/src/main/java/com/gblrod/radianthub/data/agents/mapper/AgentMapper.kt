package com.gblrod.radianthub.data.agents.mapper

import com.gblrod.radianthub.data.agents.remote.dto.AgentDto
import com.gblrod.radianthub.domain.agents.model.Ability
import com.gblrod.radianthub.domain.agents.model.Agent
import com.gblrod.radianthub.domain.agents.model.Role


fun AgentDto.toDomain() = Agent(
    uuid = uuid,
    name = displayName,
    description = description,
    icon = displayIcon,
    portrait = fullPortrait,
    background = background,

    role = role.let {
        Role(
            name = it.displayName,
            icon = it.displayIcon
        )
    },

    abilities = abilities.map {
        Ability(
            slot = it.slot,
            name = it.displayName,
            description = it.description,
            icon = it.displayIcon
        )
    }
)