package com.josegrillo.di

import com.josegrillo.data.di.modules.*
import org.koin.core.context.loadKoinModules

object DiKoinModulesLoader {
    fun initModules() {
        loadKoinModules(
            listOf(
                mapperModule,
                apiModule,
                daoModule,
                pagingModule,
                networkModule,
                repositoryModule,
                dbModule,
                httpLoggingInterceptorModule
            )
        )
    }
}