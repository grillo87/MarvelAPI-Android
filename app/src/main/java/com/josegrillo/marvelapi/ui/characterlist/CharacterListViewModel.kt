package com.josegrillo.marvelapi.ui.characterlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.josegrillo.marvelapi.base.BaseViewModel
import com.josegrillo.marvelapi.entity.CharacterVO
import com.josegrillo.marvelapi.mapper.CharacterMapper
import com.josegrillo.marvelapi.utils.Event
import com.josegrillo.usecase.usecase.GetCharactersUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CharacterListViewModel(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val characterMapper: CharacterMapper
) : BaseViewModel() {
    private val _selectedCharacterId = MutableLiveData<Event<Int>>()
    val selectedCharacterId: LiveData<Event<Int>> get() = _selectedCharacterId

    fun loadCharacters(): Flow<PagingData<CharacterVO>> {
        return getCharactersUseCase.invoke().map { pagingData ->
            pagingData.map(characterMapper::map)
        }.cachedIn(viewModelScope)
    }

    fun onCharacterSelected(characterVO: CharacterVO) {
        _selectedCharacterId.value = Event(characterVO.id)
    }
}