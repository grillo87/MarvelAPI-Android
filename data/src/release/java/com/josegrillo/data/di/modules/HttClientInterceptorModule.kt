package com.josegrillo.data.di.modules

import okhttp3.Interceptor
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

val httpLoggingInterceptorModule: Module = module {
    single<Interceptor?>(named("HttpLoggingInterceptor")) {
        null
    } bind Interceptor::class
}