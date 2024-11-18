package com.cooktogether.ui.flows.details

data class RecipeDetailsState(
    val title: String,
    val author: String,
    val ingredients: List<IngredientState> = emptyList(),
    val steps: List<String> = emptyList(),
    val imageUrl: String? = null
)

data class IngredientState(
    val name: String,
    val quantity: Double,
    val unit: String
)
