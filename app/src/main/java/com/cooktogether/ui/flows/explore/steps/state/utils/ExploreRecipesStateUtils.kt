package com.cooktogether.ui.flows.explore.steps.state.utils

import com.cooktogether.ui.flows.explore.steps.state.ExploreRecipesState
import com.cooktogether.ui.shared.components.models.CTRecipeCardComponentMeasurementsPresentation
import com.cooktogether.ui.shared.components.models.CTRecipeCardComponentPresentation

internal object ExploreRecipesStateUtils {
    fun fake(count: Int) = ExploreRecipesState(
        list = makeList(count)
    )

    private fun makeList(count: Int): List<CTRecipeCardComponentPresentation> {
        val recipeCards = mutableListOf<CTRecipeCardComponentPresentation>()

        for (i in 1..count) {
            val card = makeModel()
            recipeCards.add(card)
        }
        return recipeCards
    }

    private fun makeModel() =
        CTRecipeCardComponentPresentation(
            imageUrl = "https://static.itdg.com.br/images/622-auto/3e947dc77ac3e8275f70e73414d816d3/capa.jpg",
            prepareTime = "20min",
            recipeTitle = "Ovo Frito",
            tags = listOf("Café", "Almoço", "Café"),
            userName = "John Doe",
            favoriteRecipe = false,
            favoriteRecipeAction = { },
            measurements =
            CTRecipeCardComponentMeasurementsPresentation(
                widthCard = 183,
                fontSizeTitle = 16,
                fontSizeUserName = 12,
            ),
        )
}