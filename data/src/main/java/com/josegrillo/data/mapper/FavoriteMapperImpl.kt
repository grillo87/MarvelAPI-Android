package com.josegrillo.data.mapper

import com.josegrillo.data.entity.FavoriteDTO

class FavoriteMapperImpl : FavoriteMapper {
    override fun map(characterId: Int): FavoriteDTO {
        return FavoriteDTO(characterId)
    }
}