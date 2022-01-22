package com.josegrillo.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.josegrillo.data.database.favorite.FavoriteDAO
import com.josegrillo.data.database.favorite.FavoriteInterfaceDB

@Database(
    entities = [
        FavoriteDAO::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDAO(): FavoriteInterfaceDB
}