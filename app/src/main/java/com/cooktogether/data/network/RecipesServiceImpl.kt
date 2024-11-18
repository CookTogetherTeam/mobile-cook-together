package com.cooktogether.data.network

import com.cooktogether.data.RecipesApi
import com.cooktogether.data.models.RecipeRequest
import com.cooktogether.data.models.RecipeResponse

class RecipesServiceImpl(
    private val api: RecipesApi
) : RecipesService {

    override suspend fun fetchAllRecipes(): List<RecipeResponse> {
        return api.fetchAllRecipes()
    }

    override suspend fun getRecipeById(id: Int): RecipeResponse {
        return api.getRecipeById(id)
    }

    override suspend fun getRecipesByName(title: String): List<RecipeResponse> {
        return api.getRecipesByName(title)
    }

    override suspend fun getRecipesByCategory(categoryId: Int, title: String?): List<RecipeResponse> {
        return api.getRecipesByCategory(categoryId, title)
    }

    override suspend fun createRecipe(recipe: RecipeRequest): RecipeResponse {
        return api.createRecipe(recipe)
    }
}
