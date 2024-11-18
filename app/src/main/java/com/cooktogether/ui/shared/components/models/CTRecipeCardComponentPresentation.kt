package com.cooktogether.ui.shared.components.models

data class CTRecipeCardComponentPresentation(
    val imageUrl: String?,
    val recipeTitle: String,
    val tags: List<String>,
    val userName: String,
    val favoriteRecipe: Boolean,
    val measurements: CTRecipeCardComponentMeasurementsPresentation,
    val id: Int,
)