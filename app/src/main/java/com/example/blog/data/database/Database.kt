package com.example.blog.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavoritePost::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): FavoritePostsDao
}