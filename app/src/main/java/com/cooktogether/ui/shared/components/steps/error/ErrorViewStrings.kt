package com.cooktogether.ui.shared.components.steps.error

import androidx.compose.runtime.Composable
import com.cooktogether.ui.shared.strings.LocalStrings

data class ErrorViewStrings(
    val unknownError: String,
)

internal val errorViewStrings
    @Composable
    get() = LocalStrings.current.errorViewStrings

