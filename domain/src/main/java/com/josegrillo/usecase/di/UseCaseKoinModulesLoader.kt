package com.josegrillo.usecase.di

import com.josegrillo.usecase.di.modules.useCaseModule
import org.koin.core.context.loadKoinModules

object UseCaseKoinModulesLoader {
    fun initModules() {
        loadKoinModules(
            listOf(
                useCaseModule
            )
        )
    }
}