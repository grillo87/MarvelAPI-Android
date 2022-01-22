package com.josegrillo.data.di.modules

import com.josegrillo.data.datasource.local.GetFavoriteDataSource
import com.josegrillo.data.datasource.local.GetFavoriteDataSourceImpl
import com.josegrillo.data.datasource.remote.GetCharacterDetailDataSource
import com.josegrillo.data.datasource.remote.GetCharacterDetailDataSourceImpl
import com.josegrillo.data.datasource.remote.GetCharacterPagingSource
import org.koin.dsl.bind
import org.koin.dsl.module

val dataSourceModule = module {
    single { GetCharacterPagingSource(get()) }
    single { GetCharacterDetailDataSourceImpl(get()) } bind GetCharacterDetailDataSource::class
    single { GetFavoriteDataSourceImpl(get()) } bind GetFavoriteDataSource::class
}