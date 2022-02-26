package com.josegrillo.data.di.modules

import com.josegrillo.data.paging.CharacterPagingSource
import org.koin.dsl.module

val pagingModule = module {
    single { CharacterPagingSource(get()) }
}