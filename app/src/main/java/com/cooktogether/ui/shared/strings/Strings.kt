package com.cooktogether.ui.shared.strings

import com.cooktogether.ui.flows.details.resources.RecipeDetailsStrings
import com.cooktogether.ui.flows.onboarding.resources.OnboardingStepStrings
import com.cooktogether.ui.flows.tabNavigation.tabs.ui.resources.TabNavigationStrings
import com.cooktogether.ui.shared.components.steps.error.ErrorViewStrings
import com.cooktogether.ui.shared.components.steps.loading.LoadingViewStrings

object Locales {
    const val PT = "pt"
}

data class Strings(
    val tabNavigation: TabNavigationStrings,
    val onboardingStep: OnboardingStepStrings,
    val loadingViewStrings: LoadingViewStrings,
    val errorViewStrings: ErrorViewStrings,
    val recipeDetailsStrings: RecipeDetailsStrings,
)