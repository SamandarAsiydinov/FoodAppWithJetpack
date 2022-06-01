package com.example.mvvmrecipes.recipe_list

sealed class RecipeListEvent {
    object NewSearchEvent: RecipeListEvent()
    object NextPageEvent: RecipeListEvent()
    object RestoreStateEvent: RecipeListEvent()
}
