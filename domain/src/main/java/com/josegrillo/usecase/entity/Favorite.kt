package com.josegrillo.usecase.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Favorite.TABLE_NAME)
data class Favorite(
    @ColumnInfo(name = CHARACTER_ID_COLUMN)
    @PrimaryKey(autoGenerate = false)
    val characterId: Int
) {
    companion object {
        const val TABLE_NAME = "favorite"
        const val CHARACTER_ID_COLUMN = "characterId"
    }
}
