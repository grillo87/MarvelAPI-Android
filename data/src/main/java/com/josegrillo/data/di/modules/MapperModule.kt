package com.josegrillo.data.di.modules

import com.josegrillo.data.mapper.*
import org.koin.dsl.bind
import org.koin.dsl.module

val mapperModule = module {
    single { FavoriteMapperImpl() } bind FavoriteMapper::class
    single { CharacterMapperImpl(get()) } bind CharacterMapper::class
    single { ThumbnailMapperImpl() } bind ThumbnailMapper::class
}