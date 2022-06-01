package com.example.mvvmrecipes.recipe

sealed class RecipeEvent {
    data class GetRecipeEvent(
        val id: Int
    ) : RecipeEvent()
}