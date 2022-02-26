package com.josegrillo.marvelapi

import android.app.Application
import com.josegrillo.di.DiKoinModulesLoader
import com.josegrillo.usecase.di.UseCaseKoinModulesLoader
import com.josegrillo.marvelapi.di.AppKoinModulesLoader
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MarvelApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MarvelApplication)
            initKoinModules()
        }
    }

    private fun initKoinModules() {
        AppKoinModulesLoader.initModules()
        UseCaseKoinModulesLoader.initModules()
        DiKoinModulesLoader.initModules()
    }
}