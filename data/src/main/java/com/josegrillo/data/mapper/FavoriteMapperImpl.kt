package com.josegrillo.data.mapper

import com.josegrillo.usecase.entity.Favorite

class FavoriteMapperImpl : FavoriteMapper {
    override fun map(characterId: Int): Favorite {
        return Favorite(characterId)
    }
}