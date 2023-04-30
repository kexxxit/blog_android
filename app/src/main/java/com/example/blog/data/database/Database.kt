package com.example.blog.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.blog.data.database.entities.Token

@Database(entities = [Token::class], version = 2)
abstract class TokenDatabase : RoomDatabase() {
    abstract fun TokenDao(): TokenDao

    companion object{
        private var database: TokenDatabase ?= null

        @Synchronized
        fun getInstance(context: Context): TokenDatabase {
            return if (database == null) {
                database = Room.databaseBuilder(context, TokenDatabase::class.java, "db").build()
                database as TokenDatabase
            } else {
                database as TokenDatabase
            }
        }
    }
}