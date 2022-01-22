package com.josegrillo.usecase.di.modules

import com.josegrillo.usecase.usecase.*
import org.koin.dsl.bind
import org.koin.dsl.module

val useCaseModule = module {
    single { GetCharactersUseCaseImpl(get(), get()) } bind GetCharactersUseCase::class
    single {
        GetCharacterDetailUseCaseImpl(
            get(),
            get(),
            get()
        )
    } bind GetCharacterDetailUseCase::class
    single {
        UpdateFavoriteStatusUseCaseImpl(get(), get())
    } bind UpdateFavoriteStatusUseCase::class
}