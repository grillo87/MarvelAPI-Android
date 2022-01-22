package com.josegrillo.marvelapi.mapper

import com.josegrillo.marvelapi.entity.CharacterVO
import com.josegrillo.usecase.entity.CharacterBO

class CharacterMapperImpl : CharacterMapper {
    override fun map(input: CharacterBO) =
        CharacterVO(
            input.id,
            input.name,
            input.description,
            input.image,
            input.isFavorite
        )
}