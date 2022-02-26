package com.josegrillo.data.mapper

import com.josegrillo.usecase.entity.Favorite

interface FavoriteMapper {
    fun map(characterId: Int): Favorite
}