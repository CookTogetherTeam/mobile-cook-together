package com.cooktogether.ui.flows.tabNavigation.tabs.ui.resources

import androidx.compose.runtime.Composable
import com.cooktogether.ui.shared.strings.LocalStrings

data class TabNavigationStrings(
    val home: String,
    val search: String,
    val addRecipe: String,
    val myRecipes: String,
    val profile: String,
)

internal val tabNavigationStrings
    @Composable
    get() = LocalStrings.current.tabNavigation