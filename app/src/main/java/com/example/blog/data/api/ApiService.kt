package com.example.blog.data.api

import com.example.blog.data.model.LoginResponse
import com.example.blog.data.model.Post
import com.example.blog.data.model.User
import com.example.blog.data.model.UserModel
import retrofit2.Call
import retrofit2.http.*


interface ApiService {
    @GET("posts")
    suspend fun getPosts(): List<Post>
}