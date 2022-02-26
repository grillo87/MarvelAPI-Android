package com.josegrillo.marvelapi.di

import com.josegrillo.marvelapi.di.modules.mapperModule
import com.josegrillo.marvelapi.di.modules.viewModelModule
import org.koin.core.context.loadKoinModules

object AppKoinModulesLoader {
    fun initModules() {
        loadKoinModules(
            listOf(
                mapperModule,
                viewModelModule
            )
        )
    }
}