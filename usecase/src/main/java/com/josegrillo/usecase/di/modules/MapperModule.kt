package com.josegrillo.usecase.di.modules

import com.josegrillo.usecase.mapper.*
import org.koin.dsl.bind
import org.koin.dsl.module

val mapperModule = module {
    single { CharacterMapperImpl() } bind CharacterMapper::class
}