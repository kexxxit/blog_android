package com.example.blog.data.database

import androidx.room.*

@Dao
interface FavoritePostsDao {
    @Query("SELECT * FROM favorite_posts")
    fun getAllFavoritePosts(): List<FavoritePost>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoritePost(favoritePost: FavoritePost)

    @Query("DELETE FROM favorite_posts")
    fun deleteFavoritePosts()
}
