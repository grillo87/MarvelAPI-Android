package com.josegrillo.usecase.mapper

import com.josegrillo.data.entity.CharacterDTO
import com.josegrillo.usecase.entity.CharacterBO

class CharacterMapperImpl : CharacterMapper {
    override fun map(input: CharacterDTO): CharacterBO {
        return CharacterBO(
            input.id,
            input.name,
            input.description,
            input.thumbnail?.toString()
        )
    }

    override fun map(input: CharacterBO): CharacterDTO {
        return CharacterDTO(
            input.id,
            input.name,
            input.description,
            null
        )
    }

    override fun map(characterId: Int): CharacterDTO {
        return CharacterDTO(
            characterId,
            "",
            null,
            null
        )
    }
}