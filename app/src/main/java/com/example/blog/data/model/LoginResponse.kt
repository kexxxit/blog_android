package com.example.blog.data.model

data class LoginResponse(
    val success: Boolean,
    val data: List<User>?,
    val message: String
)