package com.example.blog.data.api

import com.example.blog.data.model.LoginResponse
import com.example.blog.data.model.Post
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiService {
    @GET("posts")
    suspend fun getPosts(): List<Post>

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String?,
        @Field("password") password: String?
    ): LoginResponse
}