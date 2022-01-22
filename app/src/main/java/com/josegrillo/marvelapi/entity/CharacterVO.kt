package com.josegrillo.marvelapi.entity

data class CharacterVO(
    val id: Int,
    val name: String,
    val description: String?,
    val image: String?,
    var isFavorite: Boolean
) {
    override fun equals(other: Any?): Boolean {
        return (other is CharacterVO)
                && id == other.id
    }

    override fun hashCode(): Int {
        return id
    }
}