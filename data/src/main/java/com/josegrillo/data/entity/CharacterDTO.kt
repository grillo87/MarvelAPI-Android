package com.josegrillo.data.entity

import com.google.gson.annotations.SerializedName

data class CharacterDTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("thumbnail")
    val thumbnailDTO: ThumbnailDTO?
)
