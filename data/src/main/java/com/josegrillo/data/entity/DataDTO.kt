package com.josegrillo.data.entity

import com.google.gson.annotations.SerializedName

data class DataDTO(
    @SerializedName("results")
    val characterDTOS: List<CharacterDTO>,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("total")
    val total: Int,
    @SerializedName("count")
    val count: Int
)
