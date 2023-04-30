package com.example.blog.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "token_table")
data class Token(@PrimaryKey val id: Int = 0, val authToken: String?, val isAdmin: Int?)