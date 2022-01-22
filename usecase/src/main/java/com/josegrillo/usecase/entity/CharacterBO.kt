package com.josegrillo.usecase.entity

data class CharacterBO(
    val id: Int,
    val name: String,
    val description: String?,
    val image: String?,
    var isFavorite: Boolean = false
)
