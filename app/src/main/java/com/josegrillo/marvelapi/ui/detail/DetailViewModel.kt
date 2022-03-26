package com.josegrillo.marvelapi.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.josegrillo.marvelapi.base.BaseViewModel
import com.josegrillo.marvelapi.entity.CharacterVO
import com.josegrillo.marvelapi.mapper.CharacterMapper
import com.josegrillo.usecase.usecase.GetCharacterDetailUseCase
import kotlinx.coroutines.Dispatchers
import com.josegrillo.usecase.entity.Result
import com.josegrillo.usecase.usecase.GetCharacterIsFavoriteUseCase
import com.josegrillo.usecase.usecase.RemoveCharacterAsFavoriteUseCase
import com.josegrillo.usecase.usecase.SaveCharacterAsFavoriteUseCase
import kotlinx.coroutines.launch

class DetailViewModel(
    private val getCharacterDetailUseCase: GetCharacterDetailUseCase,
    private val getCharacterIsFavoriteUseCase: GetCharacterIsFavoriteUseCase,
    private val saveCharacterAsFavoriteUseCase: SaveCharacterAsFavoriteUseCase,
    private val removeCharacterAsFavoriteUseCase: RemoveCharacterAsFavoriteUseCase,
    private val characterMapper: CharacterMapper
) : BaseViewModel() {

    private val _characterData = MutableLiveData<CharacterVO>()
    val characterData: LiveData<CharacterVO> get() = _characterData
    private val _characterFavorite = MutableLiveData<Boolean>()
    val characterFavorite: LiveData<Boolean> get() = _characterFavorite

    fun loadCharacterDetail(characterId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = getCharacterDetailUseCase.invoke(characterId)) {
                is Result.Success -> {
                    _characterData.postValue(
                        characterMapper.map(
                            result.data,
                            getCharacterIsFavorite(characterId)
                        )
                    )
                }
                is Result.Failure -> {
                    Log.e(tag, "Error Message ${result.exception}")
                    _errorDisplayer.postValue(result.exception)
                }
            }
        }
    }

    private suspend fun getCharacterIsFavorite(characterId: Int): Boolean {
        val isFavoriteResult = getCharacterIsFavoriteUseCase.invoke(characterId)
        return if (isFavoriteResult is Result.Success) {
            isFavoriteResult.data
        } else {
            Log.e(tag, "Error Message ${(isFavoriteResult as Result.Failure).exception}")
            _errorDisplayer.postValue(isFavoriteResult.exception)
            false
        }
    }

    fun updateUserFavoriteStatus(characterId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val isFavoriteCharacter = getCharacterIsFavorite(characterId)
            if (isFavoriteCharacter) {
                removeCharacterAsFavoriteUseCase.invoke(characterId)
            } else {
                saveCharacterAsFavoriteUseCase.invoke(characterId)
            }
            val updatedFavorite = getCharacterIsFavorite(characterId)
            _characterFavorite.postValue(updatedFavorite)
        }
    }
}