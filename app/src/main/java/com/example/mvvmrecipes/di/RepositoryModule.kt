package com.example.mvvmrecipes.di

import com.example.mvvmrecipes.model.RecipeDtoMapper
import com.example.mvvmrecipes.network.RetroService
import com.example.mvvmrecipes.repository.RecipeRepository
import com.example.mvvmrecipes.repository.RecipeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRecipeRepository(
        recipeService: RetroService,
        recipeMapper: RecipeDtoMapper,
    ): RecipeRepository{
        return RecipeRepositoryImpl (
            recipeService = recipeService,
            mapper = recipeMapper
        )
    }
}