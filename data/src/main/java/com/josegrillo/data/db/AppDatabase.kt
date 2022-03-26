package com.josegrillo.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.josegrillo.data.entity.FavoriteDTO

@Database(
    entities = [
        FavoriteDTO::class
    ],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDAO(): FavoriteDAO
}