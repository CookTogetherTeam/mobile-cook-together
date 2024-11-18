package com.cooktogether.data

import com.cooktogether.data.models.RecipeRequest
import com.cooktogether.data.models.RecipeResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipesApi {

    @GET("recipes/list/")
    suspend fun fetchAllRecipes(): List<RecipeResponse>

    @GET("recipes/recipe/id/{id}/")
    suspend fun getRecipeById(
        @Path("id") id: Int
    ): RecipeResponse

    @GET("recipes/recipe/name/{title}/")
    suspend fun getRecipesByName(
        @Path("title") title: String
    ): List<RecipeResponse>

    @GET("recipes/category/{category_id}/")
    suspend fun getRecipesByCategory(
        @Path("category_id") categoryId: Int,
        @Query("title") title: String? = null
    ): List<RecipeResponse>

    @POST("/api/recipes/create/")
    suspend fun createRecipe(@Body requestBody: RecipeRequest): RecipeResponse
}