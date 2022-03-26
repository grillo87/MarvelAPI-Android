package com.josegrillo.data.mapper

import com.josegrillo.data.entity.CharacterDTO
import com.josegrillo.usecase.entity.CharacterBO

class CharacterMapperImpl(private val thumbnailMapper: ThumbnailMapper) : CharacterMapper {
    override fun map(input: CharacterDTO): CharacterBO {
        return CharacterBO(
            input.id,
            input.name,
            input.description,
            thumbnailMapper.map(input.thumbnailDTO)
        )
    }
}