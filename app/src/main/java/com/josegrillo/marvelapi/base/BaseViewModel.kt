package com.josegrillo.marvelapi.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    protected val compositeDisposable = CompositeDisposable()
    protected val _errorDisplayer = MutableLiveData<Exception>()
    val errorDisplayer: LiveData<Exception> get() = _errorDisplayer
    protected val tag: String by lazy {
        this.javaClass::class.java.canonicalName
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}