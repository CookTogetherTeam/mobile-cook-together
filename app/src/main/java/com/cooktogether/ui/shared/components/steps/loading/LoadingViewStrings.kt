package com.cooktogether.ui.shared.components.steps.loading

import androidx.compose.runtime.Composable
import com.cooktogether.ui.shared.strings.LocalStrings

data class LoadingViewStrings(
    val getRecipes: String,
    val getCategoryRecipes: String,
    val getDetails: String,
)

internal val loadingViewStrings
    @Composable
    get() = LocalStrings.current.loadingViewStrings
