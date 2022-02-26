package com.josegrillo.data.di.modules

import com.josegrillo.data.BuildConfig
import com.josegrillo.data.utils.encrypt
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module
import java.util.concurrent.TimeUnit

private const val TIMEOUT = 30L

private val authInterceptor = { chain: Interceptor.Chain ->
    val ts = System.currentTimeMillis()

    val hash =
        "$ts${BuildConfig.MARVEL_PRIVATE_API_KEY}${BuildConfig.MARVEL_PUBLIC_API_KEY}".encrypt()
    val request = chain.request()
    val url = request.url
        .newBuilder()
        .addQueryParameter("ts", ts.toString())
        .addQueryParameter("apikey", BuildConfig.MARVEL_PUBLIC_API_KEY)
        .addQueryParameter("hash", hash)
        .build()
    val updated = request.newBuilder()
        .url(url)
        .build()

    chain.proceed(updated)
}

val networkModule: Module = module {
    single {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(authInterceptor)

        getOrNull<Interceptor>(named("HttpLoggingInterceptor"))?.let {
            okHttpClientBuilder.addInterceptor(it)
        }

        okHttpClientBuilder.build()
    } bind OkHttpClient::class
}