package com.cooktogether.ui.shared.strings

import com.cooktogether.ui.flows.onboarding.resources.OnboardingStepStrings
import com.cooktogether.ui.flows.tabNavigation.tabs.ui.resources.TabNavigationStrings

object Locales {
    const val PT = "pt"
}

data class Strings(
    val tabNavigation: TabNavigationStrings,
    val onboardingStep: OnboardingStepStrings,
)