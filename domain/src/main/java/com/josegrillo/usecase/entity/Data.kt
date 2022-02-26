package com.josegrillo.usecase.entity

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("results")
    val characters: List<Character>,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("total")
    val total: Int,
    @SerializedName("count")
    val count: Int
)
