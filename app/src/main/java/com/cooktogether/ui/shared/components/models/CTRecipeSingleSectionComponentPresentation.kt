package com.cooktogether.ui.shared.components.models

data class CTRecipeSingleSectionComponentPresentation(
    val showIndicator: Boolean = false,
    val title: String,
    val actionTitle: String,
    val action: () -> Unit,
    val items: List<CTRecipeCardComponentPresentation>,
)