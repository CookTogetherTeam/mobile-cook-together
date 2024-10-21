package com.cooktogether.ui.flows.onboarding.resources

import androidx.compose.runtime.Composable
import com.cooktogether.ui.shared.strings.LocalStrings

data class OnboardingStepStrings(
    val header: String,
    val subtitle: String,
    val firstTopic: String,
    val secondTopic: String,
    val continueButton: String,
    val loginButton: String,
)

internal val onboardingStepStrings
    @Composable
    get() = LocalStrings.current.onboardingStep