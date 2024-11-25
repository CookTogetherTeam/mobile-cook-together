package com.cooktogether.ui.flows.addrecipes.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.navigator.Navigator
import com.cooktogether.ui.flows.addrecipes.steps.RecipeNameScreen
import com.cooktogether.ui.flows.addrecipes.viewmodels.AddRecipeViewModel

@Composable
fun NewRecipeFlow() {
    val viewModel: AddRecipeViewModel = hiltViewModel()
    Navigator(RecipeNameScreen(viewModel))
}
