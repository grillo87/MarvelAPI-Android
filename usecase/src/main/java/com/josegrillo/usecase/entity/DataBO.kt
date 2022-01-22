package com.josegrillo.usecase.entity

data class DataBO(
    val charactersBO: List<CharacterBO>,
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int
)
