package com.josegrillo.marvelapi.utils

import com.josegrillo.marvelapi.R
import com.josegrillo.usecase.entity.exception.NoCharacterFoundException

fun Exception.getDisplayMessage(): Int {
    return when (this) {
        is NoCharacterFoundException -> R.string.error_no_character_found_message
        else -> R.string.error_general_message
    }
}