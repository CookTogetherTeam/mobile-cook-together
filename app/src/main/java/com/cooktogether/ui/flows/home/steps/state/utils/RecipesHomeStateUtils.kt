package com.cooktogether.ui.flows.home.steps.state.utils

import com.cooktogether.ui.flows.home.steps.state.RecipesHomeState
import com.cooktogether.ui.flows.home.steps.state.Section
import com.cooktogether.ui.shared.components.models.CTRecipeCardComponentMeasurementsPresentation
import com.cooktogether.ui.shared.components.models.CTRecipeCardComponentPresentation
import com.cooktogether.ui.shared.components.models.CTRecipeCarouselSectionComponentPresentation
import com.cooktogether.ui.shared.components.models.CTRecipeSingleSectionComponentPresentation

internal object RecipesHomeStateUtils {
    val fake =
        RecipesHomeState(
            sections =
                listOf(
                    Section.SingleRecipe(
                        model =
                            CTRecipeSingleSectionComponentPresentation(
                                title = "Almoço",
                                actionTitle = "VER MAIS",
                                item = makeSingleCard(),
                                categoryId = 1
                            ),
                    ),
                    Section.RecipeCarousel(
                        model =
                            CTRecipeCarouselSectionComponentPresentation(
                                title = "Almoço",
                                actionTitle = "VER MAIS",
                                items =
                                    listOf(
                                        makeSection(),
                                        makeSection(),
                                        makeSection(),
                                    ),
                                categoryId = 1,
                            ),
                    ),
                    Section.RecipeCarousel(
                        model =
                            CTRecipeCarouselSectionComponentPresentation(
                                title = "Almoço",
                                actionTitle = "VER MAIS",
                                items =
                                    listOf(
                                        makeSection(),
                                        makeSection(),
                                        makeSection(),
                                    ),
                                categoryId = 1,
                            ),
                    ),
                ),
        )

    fun makeSection() =
        CTRecipeCardComponentPresentation(
            imageUrl = "https://static.itdg.com.br/images/622-auto/3e947dc77ac3e8275f70e73414d816d3/capa.jpg",
            recipeTitle = "Ovo Frito",
            tags = listOf("Café", "Almoço", "Café"),
            userName = "John Doe",
            favoriteRecipe = false,
            measurements =
                CTRecipeCardComponentMeasurementsPresentation(
                    widthCard = 264,
                    fontSizeTitle = 24,
                    fontSizeUserName = 16,
                ),
            id = 1,
        )

    fun makeSingleCard() =
        makeSection().copy(
            measurements =
                CTRecipeCardComponentMeasurementsPresentation(
                    widthCard = 361,
                    fontSizeTitle = 24,
                    fontSizeUserName = 16,
                ),
        )
}