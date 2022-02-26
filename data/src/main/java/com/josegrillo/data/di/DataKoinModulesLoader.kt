package com.josegrillo.data.di

import com.josegrillo.data.di.modules.*
import org.koin.core.context.loadKoinModules

object DataKoinModulesLoader {
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