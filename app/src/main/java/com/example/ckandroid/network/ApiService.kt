package com.example.ckandroid.network

import com.example.ckandroid.data.model.UserModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    //api user
    @POST("auth/register")
    suspend fun registerUser(@Body user: UserModel): Response<UserModel>
}