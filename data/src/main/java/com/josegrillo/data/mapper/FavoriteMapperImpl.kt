package com.josegrillo.data.mapper

import com.josegrillo.data.database.favorite.FavoriteDAO
import com.josegrillo.data.entity.CharacterDTO

class FavoriteMapperImpl : FavoriteMapper {
    override fun map(input: CharacterDTO): FavoriteDAO {
        return FavoriteDAO(
            input.id
        )
    }
}