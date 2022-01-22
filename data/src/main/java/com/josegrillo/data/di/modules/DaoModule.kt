package com.josegrillo.data.di.modules

import com.josegrillo.data.database.AppDatabase
import org.koin.dsl.module

val daoModule = module {
    single { get<AppDatabase>().favoriteDAO() }
}