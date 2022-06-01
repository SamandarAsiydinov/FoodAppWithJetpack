package com.example.mvvmrecipes.ui.theme.view

import android.nfc.tech.MifareUltralight.PAGE_SIZE
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.mvvmrecipes.model.Recipe
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@Composable
fun RecipeList(
    loading: Boolean,
    recipes: List<Recipe>,
    onChangeScrollPosition: (Int) -> Unit,
    page: Int,
    onTriggerNextPage: () -> Unit,
    onNavigateToRecipeDetailScreen: (Int) -> Unit
) {
    Box(modifier = Modifier.background(color = MaterialTheme.colors.surface)) {
        if (loading && recipes.isEmpty()) {
            HorizontalDottedProgressBar()
        } else if (recipes.isEmpty()){
            NothingHere()
        } else {
            LazyColumn {
                itemsIndexed(
                    items = recipes
                ) { index, recipe ->
                    onChangeScrollPosition(index)
                    if ((index + 1) >= (page * PAGE_SIZE) && !loading) {
                        onTriggerNextPage()
                    }
                    RecipeCard(recipe = recipe) {
                        onNavigateToRecipeDetailScreen(recipe.id)
                    }
                }
            }
        }
    }
}