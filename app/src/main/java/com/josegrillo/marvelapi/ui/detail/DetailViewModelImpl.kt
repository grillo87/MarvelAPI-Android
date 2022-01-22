package com.josegrillo.marvelapi.ui.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.josegrillo.marvelapi.entity.CharacterVO
import com.josegrillo.marvelapi.mapper.CharacterMapper
import com.josegrillo.usecase.usecase.GetCharacterDetailUseCase
import com.josegrillo.usecase.usecase.UpdateFavoriteStatusUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailViewModelImpl(
    private val getCharacterDetailUseCase: GetCharacterDetailUseCase,
    private val updateFavoriteStatusUseCase: UpdateFavoriteStatusUseCase,
    private val characterMapper: CharacterMapper
) : DetailViewModel, ViewModel() {

    private val TAG = DetailViewModelImpl::class.java.canonicalName
    private val compositeDisposable = CompositeDisposable()
    private val mutableCharacterData = MutableLiveData<CharacterVO>()
    private val mutableErrorDisplayer = MutableLiveData<Boolean>()
    private val mutableCharacterFavorite = MutableLiveData<Boolean>()
    private val mutableErrorFavoriteDisplayer = MutableLiveData<Boolean>()
    private var isCharacterFavorite = false

    override fun loadCharacterDetail(characterId: Int) {
        compositeDisposable.add(
            getCharacterDetailUseCase.getCharacterDetail(characterId)
                .map(characterMapper::map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    isCharacterFavorite = it.isFavorite
                    mutableCharacterData.value = it
                }, {
                    mutableErrorDisplayer.value = true
                    Log.e(TAG, it.localizedMessage)
                })
        )
    }

    override fun getCharacterData() = mutableCharacterData

    override fun getDetailErrorDisplayer() = mutableErrorDisplayer

    override fun updateUserFavoriteStatus(characterId: Int) {
        compositeDisposable.add(
            updateFavoriteStatusUseCase.updateFavoriteStatus(characterId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    isCharacterFavorite = !isCharacterFavorite
                    mutableCharacterFavorite.value = isCharacterFavorite
                }, {
                    mutableErrorFavoriteDisplayer.value = true
                    Log.e(TAG, it.localizedMessage)
                })
        )
    }

    override fun getCharacterFavoriteStatusUpdate() = mutableCharacterFavorite

    override fun getCharacterFavoriteErrorDisplayer() = mutableErrorFavoriteDisplayer

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}