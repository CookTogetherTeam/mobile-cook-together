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
import com.cooktogether.ui.flows.explore.steps.ExploreListCardsStep
import com.cooktogether.ui.flows.explore.steps.state.utils.ExploreRecipesStateUtils
import com.cooktogether.ui.flows.tabNavigation.tabs.custom.CookTogetherTab
import com.cooktogether.ui.flows.tabNavigation.tabs.ui.resources.tabNavigationStrings

object SearchTab : CookTogetherTab {
    private fun readResolve(): Any = HomeTab

    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(ImageVector.vectorResource(R.drawable.ic_search))
            val tabOption =
                TabOptions(
                    index = 0u,
                    title = tabNavigationStrings.search,
                    icon = icon,
                )
            return remember { tabOption }
        }

    @Composable
    override fun Content(innerPadding: PaddingValues) {
        val items = ExploreRecipesStateUtils.fake(20)
        ExploreListCardsStep(
            modifier = Modifier.padding(innerPadding),
            state = items,
        )
    }
}