package com.cooktogether.ui.flows.tabNavigation.tabs.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.vectorResource
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.cooktogether.R
import com.cooktogether.common.utils.RequestResult
import com.cooktogether.ui.flows.explore.steps.ExploreListCardsScreen
import com.cooktogether.ui.flows.home.steps.RecipesHomeStep
import com.cooktogether.ui.flows.home.steps.state.RecipesHomeState
import com.cooktogether.ui.flows.home.viewmodels.RecipesHomeViewModel
import com.cooktogether.ui.flows.tabNavigation.tabs.custom.CookTogetherTab
import com.cooktogether.ui.flows.tabNavigation.tabs.ui.resources.tabNavigationStrings
import com.cooktogether.ui.shared.components.steps.error.ErrorView
import com.cooktogether.ui.shared.components.steps.error.errorViewStrings
import com.cooktogether.ui.shared.components.steps.loading.LoadingView
import com.cooktogether.ui.shared.components.steps.loading.loadingViewStrings

object HomeTab : CookTogetherTab {
    private fun readResolve(): Any = HomeTab

    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(ImageVector.vectorResource(R.drawable.ic_home))
            val option =
                TabOptions(
                    index = 0u,
                    title = tabNavigationStrings.home,
                    icon = icon,
                )
            return remember { option }
        }

    @Composable
    override fun Content(innerPadding: PaddingValues) {
        val viewModel: RecipesHomeViewModel = hiltViewModel()
        val recipesState by viewModel.recipesHomeState.observeAsState()
        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(Unit) {
            viewModel.fetchRecipes()
        }

        when (val state = recipesState) {
            is RequestResult.Loading -> {
                LoadingView(loadingViewStrings.getRecipes)
            }
            is RequestResult.Success<RecipesHomeState> -> {
                RecipesHomeStep(
                    modifier = Modifier.padding(innerPadding),
                    state = state.data,
                    onExploreCategoryClick = { id, title ->
                        navigator.push(
                            ExploreListCardsScreen(
                                id,
                                title
                            )
                        )
                    }
                )
            }
            is RequestResult.Error -> {
                ErrorView(state.exception.localizedMessage)
            }

            null -> ErrorView(errorViewStrings.unknownError)
        }
    }
}