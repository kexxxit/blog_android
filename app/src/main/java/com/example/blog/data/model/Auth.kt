package com.example.blog.data.model

data class User(
    val token: String?,
    val id: Int?,
    val name: String?,
    val email: String?,
    val email_verified_at: String?,
    val remember_token: String?,
    val created_at: String?,
    val updated_at: String?,
    val isAdmin: Int?
)
