package com.cooktogether.ui.flows.addrecipes.steps

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.cooktogether.ui.flows.addrecipes.viewmodels.AddRecipeViewModel
import com.cooktogether.ui.flows.tabNavigation.tabs.ui.HomeTab

class RecipeNameScreen(
    val viewModel: AddRecipeViewModel
) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        RecipeNameStep(
            onNext = { navigator.push(CategoryScreen(viewModel)) },
            viewModel = viewModel
        )
    }
}

class CategoryScreen(
    val viewModel: AddRecipeViewModel
) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        AddCategoryStep(
            onCategorySelected = { category ->
                viewModel.selectedCategory = category

                navigator.push(IngredientsScreen(viewModel))
            }
        )
    }
}

class IngredientsScreen(
    val viewModel: AddRecipeViewModel
) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        AddIngredientsStep(
            onNext = { navigator.push(StepsScreen(viewModel)) },
            viewModel = viewModel
        )
    }
}

class StepsScreen(
    val viewModel: AddRecipeViewModel
) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        StepByStepScreen(
            onNext = { navigator.push(ImageScreen(viewModel)) },
            viewModel = viewModel
        )
    }
}

class ImageScreen(
    val viewModel: AddRecipeViewModel
) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val recipeSaved by viewModel.recipeSaved.observeAsState()

        LaunchedEffect(viewModel, recipeSaved) {
            if (recipeSaved == null) return@LaunchedEffect
            if (recipeSaved == true) {
                navigator.push(
                    RecipeSavedScreen()
                )
            } else {
                navigator.push(
                    RecipeSaveErrorScreen(
                        onButtonClick = {
                            navigator.replaceAll(listOf(RecipeNameScreen(viewModel)))
                        }
                    )
                )
            }
            viewModel._recipeSaved.postValue(null)
        }

        RecipeImageStep(
            onFinish = {
                viewModel.addRecipe()
            },
            viewModel = viewModel
        )
    }
}