package com.josegrillo.usecase.di.modules

import com.josegrillo.usecase.usecase.*
import org.koin.dsl.module

val useCaseModule = module {
    single { GetCharactersUseCase(get()) }
    single { GetCharacterDetailUseCase(get()) }
    single { GetCharacterIsFavoriteUseCase(get()) }
    single { RemoveCharacterAsFavoriteUseCase(get()) }
    single { SaveCharacterAsFavoriteUseCase(get()) }
}