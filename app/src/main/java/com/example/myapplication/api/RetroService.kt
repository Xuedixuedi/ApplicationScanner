package com.example.myapplication.api

import com.example.myapplication.model.RecyclerList
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {
    @GET("repositories")
    suspend fun getDataFromApi(@Query("q") query: String): RecyclerList
}