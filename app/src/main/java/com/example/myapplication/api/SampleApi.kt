package com.example.myapplication.api

import com.example.myapplication.model.Post
import retrofit2.Response
import retrofit2.http.GET

interface SampleApi {

    @GET("postdhkjdhs/1")
    suspend fun getPost(): Response<Post>
}