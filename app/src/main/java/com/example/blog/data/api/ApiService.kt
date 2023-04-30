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

    @Headers("Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiI1IiwianRpIjoiMmU4OWQwNDVmNTQ4ZTU4YzhiYmQ4OWI1OTgzNzNjNDU5MWY3ZDE2YmE3NmVhZDBjZjUzYjAxYTY1OWE0ZGRiMzM4ZTI5ZjE2M2FlZGU5Y2EiLCJpYXQiOjE2ODI4NDk4NzMuNTA1MjQ3MTE2MDg4ODY3MTg3NSwibmJmIjoxNjgyODQ5ODczLjUwNTI0OTk3NzExMTgxNjQwNjI1LCJleHAiOjE2OTg2NjEwNzMuNTAzNzcxMDY2NjY1NjQ5NDE0MDYyNSwic3ViIjoiMjYiLCJzY29wZXMiOltdfQ.Tjz_Ez1kbwcUZGq-6LzoYgSBOK74SCBk8jCIaqO7qG6JeLfAM1XBVdp_y4QiBgd5e0qPoY4i6jR3K14Pwjg2UtpIbnlF4uWd0buKd93M9en8IHPhtjtx-9SZWHDtEQp_dXutuSPHRkaijN4lMgdA1WvqjquXQQnkMpnivkLRgLuVuPfDpoJGXv79zQ8vQyrZZvjwmQISc_qS7Q-JoxTmi9Iy0PX-zGmoGESoB_AdYIjk1C-oD22vRXWvnNM_UXxWQOd3VGRtwDAqht3EaHnAK7FFSimwk5TCylQ3ZuX_ZTDthLh2axBiyaHmJ3K6jgGSfBeDqpnPFchnwmFyfnm8hpKn9-prouRg2rf01rj7YogJYYfgxum1HtMgaf66mqJ8cZR-jIFCGOQpGPWYZ2GHI8rmDESF2U_R_4ejHMjOMn5cyLZ27SdLHJbCsy9j7zav2QulBUpQcd6gZAyiHdSHHuibTAWUHVz45SJbCywCTVsxAjwMwPR8INPQ_I0BRp8lFUMlzb6N0bHuZQlG9gc83--VHUd67C0NwL8wK1msfLgwZHBpmK0uHrcZBEdU11I2Lxop9AFLXXR7iorCpoqJVJm4JDsYDAVD6eOKzLL378kymz5E9XIwGqHBWG6aAUNmO2j7JEVyZLy1WidzThfhmfz3uRbIBGZEReq1quD2fww")
    @GET("user")
    suspend fun getUser(): UserModel

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String?,
        @Field("password") password: String?
    ): LoginResponse
}