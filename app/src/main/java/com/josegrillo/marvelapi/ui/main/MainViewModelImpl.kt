package com.josegrillo.marvelapi.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import androidx.paging.map
import com.josegrillo.marvelapi.entity.CharacterVO
import com.josegrillo.marvelapi.mapper.CharacterMapper
import com.josegrillo.usecase.usecase.GetCharactersUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModelImpl(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val characterMapper: CharacterMapper
) : MainViewModel,
    ViewModel() {

    private val TAG = MainViewModelImpl::class.java.canonicalName
    private val compositeDisposable = CompositeDisposable()
    private val mutableCharactersData = MutableLiveData<PagingData<CharacterVO>>()
    private val mutableCharacterSelected = MutableLiveData<CharacterVO>()
    private val mutableErrorDisplayer = MutableLiveData<Boolean>()

    override fun loadData() {
        compositeDisposable.add(
            getCharactersUseCase.getCharacters()
                .map { it.map(characterMapper::map) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    mutableCharactersData.value = it
                }, {
                    mutableErrorDisplayer.value = true
                    Log.e(TAG, it.localizedMessage)
                })
        )
    }

    override fun getCharactersData() = mutableCharactersData

    override fun onCharacterSelected(characterVO: CharacterVO) {
        mutableCharacterSelected.value = characterVO
    }

    override fun getCharacterSelectedData() = mutableCharacterSelected

    override fun getDataError() = mutableErrorDisplayer

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}