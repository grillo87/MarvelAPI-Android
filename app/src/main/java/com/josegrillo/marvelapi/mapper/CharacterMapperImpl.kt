package com.josegrillo.marvelapi.mapper

import com.josegrillo.marvelapi.entity.CharacterVO
import com.josegrillo.usecase.entity.CharacterBO

class CharacterMapperImpl : CharacterMapper {
    override fun map(input: CharacterBO, isFavorite: Boolean): CharacterVO {
        return CharacterVO(
            input.id,
            input.name,
            input.description,
            input.thumbnail,
            isFavorite
        )
    }
}