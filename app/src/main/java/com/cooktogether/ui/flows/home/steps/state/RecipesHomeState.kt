package com.cooktogether.ui.flows.home.steps.state

import com.cooktogether.ui.shared.components.models.CTRecipeCarouselSectionComponentPresentation
import com.cooktogether.ui.shared.components.models.CTRecipeListSectionComponentPresentation
import com.cooktogether.ui.shared.components.models.CTRecipeSingleSectionComponentPresentation

data class RecipesHomeState(
    val sections: List<Section>,
)

sealed class Section {
    data class SingleRecipe(
        val model: CTRecipeSingleSectionComponentPresentation,
    ) : Section()

    data class RecipeCarousel(
        val model: CTRecipeCarouselSectionComponentPresentation,
    ) : Section()

    data class RecipeList(
        val model: CTRecipeListSectionComponentPresentation,
    ) : Section()
}