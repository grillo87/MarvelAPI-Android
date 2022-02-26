package com.josegrillo.data.di.modules

import androidx.room.Room
import com.josegrillo.data.BuildConfig
import com.josegrillo.data.db.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dbModule = module {

    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, BuildConfig.APP_DATABASE)
            .build()
    }
}