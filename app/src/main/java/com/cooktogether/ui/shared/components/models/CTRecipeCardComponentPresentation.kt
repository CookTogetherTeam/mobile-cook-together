package com.cooktogether.ui.shared.components.models

data class CTRecipeCardComponentPresentation(
    val imageUrl: String,
    val prepareTime: String,
    val recipeTitle: String,
    val tags: List<String>,
    val userName: String,
    val favoriteRecipe: Boolean,
    val favoriteRecipeAction: () -> Unit,
    val measurements: CTRecipeCardComponentMeasurementsPresentation,
)