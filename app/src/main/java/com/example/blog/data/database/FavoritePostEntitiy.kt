package com.example.blog.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_posts")
data class FavoritePost(
    @PrimaryKey val postId: Int = 0,
    val postImg: String?,
    val postText: String?
)