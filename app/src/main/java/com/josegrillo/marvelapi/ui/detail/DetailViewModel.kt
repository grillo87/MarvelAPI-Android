package com.josegrillo.marvelapi.ui.detail

import androidx.lifecycle.LiveData
import com.josegrillo.marvelapi.entity.CharacterVO

interface DetailViewModel {
    fun loadCharacterDetail(characterId: Int)
    fun getCharacterData(): LiveData<CharacterVO>
    fun getDetailErrorDisplayer(): LiveData<Boolean>
    fun updateUserFavoriteStatus(characterId: Int)
    fun getCharacterFavoriteStatusUpdate(): LiveData<Boolean>
    fun getCharacterFavoriteErrorDisplayer(): LiveData<Boolean>
}