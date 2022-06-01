package com.example.mvvmrecipes.repository

import com.example.mvvmrecipes.model.Recipe

interface RecipeRepository {
    suspend fun search(token: String, page: Int, query: String): List<Recipe>

    suspend fun get(token: String, id: Int): Recipe
}