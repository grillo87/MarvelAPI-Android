package com.josegrillo.data.database.favorite

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = FavoriteDAO.TABLE_NAME)
data class FavoriteDAO(
    @ColumnInfo(name = FavoriteDAO.CHARACTER_ID_COLUMN)
    @PrimaryKey(autoGenerate = false)
    val characterId: Int
) {
    companion object {
        const val TABLE_NAME = "favorite"
        const val CHARACTER_ID_COLUMN = "characterId"
    }
}
