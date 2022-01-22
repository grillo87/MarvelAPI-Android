package com.josegrillo.marvelapi.di.modules

import com.josegrillo.marvelapi.ui.detail.DetailViewModel
import com.josegrillo.marvelapi.ui.detail.DetailViewModelImpl
import com.josegrillo.marvelapi.ui.main.MainViewModel
import com.josegrillo.marvelapi.ui.main.MainViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModelImpl(get(), get()) } bind MainViewModel::class
    viewModel { DetailViewModelImpl(get(), get(), get()) } bind DetailViewModel::class
}