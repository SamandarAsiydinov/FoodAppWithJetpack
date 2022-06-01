package com.example.mvvmrecipes.network

import com.example.mvvmrecipes.model.RecipeDto
import com.example.mvvmrecipes.model.RecipeSearchResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RetroService {

    @GET("search")
    suspend fun search(
        @Header("Authorization") token: String,
        @Query("page") page: Int,
        @Query("query") query: String
    ): RecipeSearchResponse

    @GET("get")
    suspend fun getFoodList(
        @Header("Authorization") token: String,
        @Query("id") id: Int
    ): RecipeDto
}