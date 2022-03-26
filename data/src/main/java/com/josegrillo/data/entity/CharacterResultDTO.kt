package com.josegrillo.data.entity

import com.google.gson.annotations.SerializedName

data class CharacterResultDTO(
    @SerializedName("data")
    val dataDTO: DataDTO
)
