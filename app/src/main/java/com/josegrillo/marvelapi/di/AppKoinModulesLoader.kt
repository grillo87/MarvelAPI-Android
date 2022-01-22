package com.josegrillo.marvelapi.di.modules

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