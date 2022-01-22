package com.josegrillo.marvelapi

import android.app.Application
import com.josegrillo.data.di.DataKoinModulesLoader
import com.josegrillo.usecase.di.UseCaseKoinModulesLoader
import com.josegrillo.marvelapi.di.modules.AppKoinModulesLoader
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
        DataKoinModulesLoader.initModules()
    }
}