package com.josegrillo.usecase.entity

import com.google.gson.annotations.SerializedName

data class CharacterResult(
    @SerializedName("data")
    val data: Data
)
