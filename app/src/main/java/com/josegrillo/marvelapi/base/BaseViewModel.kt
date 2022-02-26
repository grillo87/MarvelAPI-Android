package com.josegrillo.marvelapi.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    protected val _errorDisplayer = MutableLiveData<Exception>()
    val errorDisplayer: LiveData<Exception> get() = _errorDisplayer
    protected val tag: String by lazy {
        this.javaClass::class.java.canonicalName
    }
}