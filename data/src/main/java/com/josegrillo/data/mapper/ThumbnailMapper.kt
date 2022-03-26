package com.josegrillo.data.mapper

import com.josegrillo.data.entity.ThumbnailDTO

interface ThumbnailMapper {
    fun map(input: ThumbnailDTO?): String?
}