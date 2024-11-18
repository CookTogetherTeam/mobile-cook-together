package com.cooktogether.ui.flows.details.resources

import androidx.compose.runtime.Composable
import com.cooktogether.ui.shared.strings.LocalStrings


data class RecipeDetailsStrings(
    val ingredients: String,
    val preparationSteps: String,
)

internal val recipeDetailsStrings
    @Composable
    get() = LocalStrings.current.recipeDetailsStrings
