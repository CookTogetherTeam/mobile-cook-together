package com.cooktogether.data.network

import com.cooktogether.data.models.RecipeRequest
import com.cooktogether.data.models.RecipeResponse

interface RecipesService {
    suspend fun fetchAllRecipes(): List<RecipeResponse>
    suspend fun getRecipeById(id: Int): RecipeResponse
    suspend fun getRecipesByName(title: String): List<RecipeResponse>
    suspend fun getRecipesByCategory(categoryId: Int, title: String? = null): List<RecipeResponse>
    suspend fun createRecipe(recipe: RecipeRequest) : RecipeResponse
}