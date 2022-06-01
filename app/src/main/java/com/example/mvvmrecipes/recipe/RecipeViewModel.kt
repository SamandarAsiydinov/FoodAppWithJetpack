package com.example.mvvmrecipes.recipe

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmrecipes.model.Recipe
import com.example.mvvmrecipes.recipe.RecipeEvent.*
import com.example.mvvmrecipes.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

const val STATE_KEY_RECIPE = "recipe.state.recipe.key"

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository,
    @Named("auth_token") private val token: String,
    private val state: SavedStateHandle
) : ViewModel() {
    val recipe: MutableState<Recipe?> = mutableStateOf(null)
    val loading = mutableStateOf(false)

    init {
        state.get<Int>(STATE_KEY_RECIPE)?.let {
            onTriggerEvent(GetRecipeEvent(it))
        }
    }

    fun onTriggerEvent(event: RecipeEvent) {
        viewModelScope.launch {
            try {
                when (event) {
                    is GetRecipeEvent -> {
                        if (recipe.value == null) {
                            getRecipeEvent(event.id)
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("@@@@@", "launchJob: Exception: ${e}, ${e.cause}")
                e.printStackTrace()
            }
        }
    }

    private suspend fun getRecipeEvent(id: Int) {
        loading.value = true
        delay(1000)
        val recipe = recipeRepository.get(token, id)
        this.recipe.value = recipe
        state.set(STATE_KEY_RECIPE, recipe.id)

        loading.value = false
    }
}