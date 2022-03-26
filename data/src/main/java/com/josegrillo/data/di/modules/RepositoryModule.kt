package com.josegrillo.data.di.modules

import com.josegrillo.data.repository.CharactersRepositoryImpl
import com.josegrillo.data.repository.datasource.local.CharacterLocalDataSource
import com.josegrillo.data.repository.datasource.local.CharacterLocalDataSourceImpl
import com.josegrillo.data.repository.datasource.remote.CharacterRemoteDataSource
import com.josegrillo.data.repository.datasource.remote.CharacterRemoteDataSourceImpl
import com.josegrillo.usecase.repository.CharactersRepository
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    single {
        CharactersRepositoryImpl(
            get(),
            get(),
            get(),
            get()
        )
    } bind CharactersRepository::class

    single { CharacterRemoteDataSourceImpl(get(), get()) } bind CharacterRemoteDataSource::class

    single { CharacterLocalDataSourceImpl(get()) } bind CharacterLocalDataSource::class
}