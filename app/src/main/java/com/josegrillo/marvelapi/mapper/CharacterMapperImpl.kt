package com.josegrillo.marvelapi.mapper

import com.josegrillo.marvelapi.entity.CharacterVO
import com.josegrillo.usecase.entity.Character
import com.josegrillo.marvelapi.utils.makeSecurePath

class CharacterMapperImpl : CharacterMapper {
    override fun map(input: Character, isFavorite: Boolean): CharacterVO {
        return CharacterVO(
            input.id,
            input.name,
            input.description,
            "${input.thumbnail?.path?.makeSecurePath()}.${input.thumbnail?.extension}",
            isFavorite
        )
    }
}