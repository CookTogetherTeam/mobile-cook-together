package com.cooktogether.ui.flows.tabNavigation.tabs.ui

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
import com.cooktogether.ui.flows.home.steps.RecipesHomeStep
import com.cooktogether.ui.flows.home.steps.state.utils.RecipesHomeStateUtils
import com.cooktogether.ui.flows.tabNavigation.tabs.custom.CookTogetherTab
import com.cooktogether.ui.flows.tabNavigation.tabs.ui.resources.tabNavigationStrings

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
        RecipesHomeStep(
            modifier = Modifier.padding(innerPadding),
            state = RecipesHomeStateUtils.fake,
        )
    }
}