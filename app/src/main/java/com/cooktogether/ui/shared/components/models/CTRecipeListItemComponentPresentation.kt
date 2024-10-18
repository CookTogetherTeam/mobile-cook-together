package com.cooktogether.ui.shared.components.models

data class CTRecipeListItemComponentPresentation(
    val imageUrl: String,
    val recipeTitle: String,
    val prepareTime: String,
    val favoriteRecipe: Boolean,
    val favoriteRecipeAction: () -> Unit,
)