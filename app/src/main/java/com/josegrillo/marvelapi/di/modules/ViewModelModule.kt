package com.josegrillo.marvelapi.di.modules

import com.josegrillo.marvelapi.ui.characterlist.CharacterListViewModel
import com.josegrillo.marvelapi.ui.detail.DetailViewModel
import com.josegrillo.marvelapi.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { CharacterListViewModel(get(), get()) }
    viewModel { DetailViewModel(get(), get(), get(), get(), get()) }
}