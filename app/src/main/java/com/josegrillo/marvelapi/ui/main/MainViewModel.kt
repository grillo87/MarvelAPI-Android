package com.josegrillo.marvelapi.ui.main

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.josegrillo.marvelapi.entity.CharacterVO

interface MainViewModel {
    fun loadData()
    fun getCharactersData(): LiveData<PagingData<CharacterVO>>
    fun onCharacterSelected(characterVO: CharacterVO)
    fun getCharacterSelectedData(): LiveData<CharacterVO>
    fun getDataError(): LiveData<Boolean>
}