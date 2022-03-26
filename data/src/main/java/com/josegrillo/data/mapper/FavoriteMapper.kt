package com.josegrillo.data.mapper

import com.josegrillo.data.entity.FavoriteDTO

interface FavoriteMapper {
    fun map(characterId: Int): FavoriteDTO
}