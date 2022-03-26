package com.josegrillo.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = FavoriteDTO.TABLE_NAME)
data class FavoriteDTO(
    @ColumnInfo(name = CHARACTER_ID_COLUMN)
    @PrimaryKey(autoGenerate = false)
    val characterId: Int
) {
    companion object {
        const val TABLE_NAME = "favorite"
        const val CHARACTER_ID_COLUMN = "characterId"
    }
}
