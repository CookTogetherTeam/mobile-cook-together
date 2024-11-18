package com.cooktogether.ui.flows.addrecipes.steps

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.LocalNavigator
import com.cooktogether.ui.flows.addrecipes.viewmodels.AddRecipeViewModel
import com.cooktogether.ui.flows.tabNavigation.tabs.ui.HomeTab

class RecipeNameScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: AddRecipeViewModel = hiltViewModel()
        RecipeNameStep(
            onNext = { navigator.push(IngredientsScreen()) },
            viewModel = viewModel
        )
    }
}

class IngredientsScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: AddRecipeViewModel = hiltViewModel()
        AddIngredientsStep(
            onNext = { navigator.push(StepsScreen()) },
            viewModel = viewModel
        )
    }
}

class StepsScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: AddRecipeViewModel = hiltViewModel()
        StepByStepScreen(
            onNext = { navigator.push(ImageScreen()) },
            viewModel = viewModel
        )
    }
}

class ImageScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: AddRecipeViewModel = hiltViewModel()
        val recipeSaved by viewModel.recipeSaved.observeAsState()

        RecipeImageStep(onFinish = {
            viewModel.addRecipe()
            if (recipeSaved == true) {
                navigator.push(
                    RecipeSavedScreen(
                        onButtonClick = {
                            navigator.push(HomeTab)
                        }
                    )
                )
            } else {
                navigator.push(
                    RecipeSaveErrorScreen(
                        onButtonClick = {
                            navigator.replaceAll(listOf(RecipeNameScreen()))
                        }
                    )
                )
            }
        })
    }
}

