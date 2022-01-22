package com.josegrillo.data.di.modules

import com.josegrillo.data.mapper.FavoriteMapper
import com.josegrillo.data.mapper.FavoriteMapperImpl
import org.koin.dsl.bind
import org.koin.dsl.module

val mapperModule = module {
    single { FavoriteMapperImpl() } bind FavoriteMapper::class
}