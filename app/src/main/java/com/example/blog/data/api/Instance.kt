package com.example.blog.data.api

import com.example.blog.data.model.LoginResponse
import com.google.gson.GsonBuilder
import com.google.gson.InstanceCreator
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type


object Instance {

    private val retrofit by lazy {
        Retrofit
            .Builder()
            .baseUrl("http://kvashear.beget.tech/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}