package com.example.mvvmrecipes.repository

import com.example.mvvmrecipes.model.Recipe
import com.example.mvvmrecipes.model.RecipeDtoMapper
import com.example.mvvmrecipes.network.RetroService

class RecipeRepositoryImpl(
    private val recipeService: RetroService,
    private val mapper: RecipeDtoMapper
): RecipeRepository {
    override suspend fun search(token: String, page: Int, query: String): List<Recipe> {
        return mapper.toDomainList(recipeService.search(token, page, query).recipes)
    }

    override suspend fun get(token: String, id: Int): Recipe {
        return mapper.mapToDomainModel(recipeService.getFoodList(token, id))
    }
}