package com.josegrillo.marvelapi.di.modules

import com.josegrillo.marvelapi.mapper.CharacterMapper
import com.josegrillo.marvelapi.mapper.CharacterMapperImpl
import org.koin.dsl.bind
import org.koin.dsl.module

val mapperModule = module {
    single { CharacterMapperImpl() } bind CharacterMapper::class
}