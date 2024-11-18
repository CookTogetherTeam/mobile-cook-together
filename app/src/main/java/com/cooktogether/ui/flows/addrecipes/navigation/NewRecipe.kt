package com.cooktogether.ui.flows.addrecipes.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.cooktogether.ui.flows.addrecipes.steps.RecipeNameScreen

@Composable
fun NewRecipeFlow() {
    Navigator(RecipeNameScreen())
}
