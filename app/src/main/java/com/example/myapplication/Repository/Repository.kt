package com.example.myapplication.Repository

import com.example.myapplication.api.RetrofitInstance
import com.example.myapplication.model.Post
import retrofit2.Response

class Repository {


    suspend fun getPost(): Response<Post> {
        return RetrofitInstance.api.getPost()
    }
}