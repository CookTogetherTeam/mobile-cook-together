package com.cooktogether.ui.flows.tabNavigation.tabs.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.vectorResource
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.cooktogether.R
import com.cooktogether.ui.flows.addrecipes.navigation.NewRecipeFlow
import com.cooktogether.ui.flows.home.steps.RecipesHomeStep
import com.cooktogether.ui.flows.home.steps.state.utils.RecipesHomeStateUtils
import com.cooktogether.ui.flows.tabNavigation.tabs.custom.CookTogetherTab
import com.cooktogether.ui.flows.tabNavigation.tabs.ui.resources.tabNavigationStrings

object AddRecipeTab : CookTogetherTab {
    private fun readResolve(): Any = HomeTab

    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(ImageVector.vectorResource(R.drawable.ic_add))
            val option =
                TabOptions(
                    index = 2u,
                    title = tabNavigationStrings.addRecipe,
                    icon = icon,
                )
            return remember { option }
        }

    @Composable
    override fun Content(innerPadding: PaddingValues) {
        Box(modifier = Modifier.padding(innerPadding)) {
            NewRecipeFlow()
        }
    }
}