package com.josegrillo.data.mapper

import com.josegrillo.data.database.favorite.FavoriteDAO
import com.josegrillo.data.entity.CharacterDTO

interface FavoriteMapper {
    fun map(input: CharacterDTO): FavoriteDAO
}