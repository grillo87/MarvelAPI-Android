package com.josegrillo.data.entity

import com.google.gson.annotations.SerializedName

data class ResultDTO(
    @SerializedName("data")
    val dataDTO: DataDTO
)
