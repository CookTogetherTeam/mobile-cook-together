package com.cooktogether.data.models

import com.google.gson.annotations.SerializedName

data class RecipeResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("user")
    val user: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("ingredients")
    val ingredients: List<IngredientsResponse> = emptyList(),
    @SerializedName("text_area")
    val preparationMethod: List<String> = emptyList(),
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("category")
    val category: Int,
    @SerializedName("category_name")
    val categoryName: String
)

