package com.cooktogether.ui.shared.components.models

data class CTRecipeCarouselSectionComponentPresentation(
    val title: String,
    val actionTitle: String,
    val items: List<CTRecipeCardComponentPresentation>,
)