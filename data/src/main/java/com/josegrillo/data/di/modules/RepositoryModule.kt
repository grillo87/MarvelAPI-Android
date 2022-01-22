package com.josegrillo.data.di.modules

import com.josegrillo.data.repository.CharactersRepository
import com.josegrillo.data.repository.CharactersRepositoryImpl
import com.josegrillo.data.repository.FavoriteRepository
import com.josegrillo.data.repository.FavoriteRepositoryImpl
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    single {
        CharactersRepositoryImpl(
            get(),
            get()
        )
    } bind CharactersRepository::class
    single { FavoriteRepositoryImpl(get(), get()) } bind FavoriteRepository::class
}