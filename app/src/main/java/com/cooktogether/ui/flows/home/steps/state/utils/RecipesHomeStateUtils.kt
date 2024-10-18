package com.cooktogether.ui.flows.home.steps.state.utils

import com.cooktogether.ui.flows.home.steps.state.RecipesHomeState
import com.cooktogether.ui.flows.home.steps.state.Section
import com.cooktogether.ui.shared.components.models.CTRecipeCardComponentMeasurementsPresentation
import com.cooktogether.ui.shared.components.models.CTRecipeCardComponentPresentation
import com.cooktogether.ui.shared.components.models.CTRecipeCarouselSectionComponentPresentation
import com.cooktogether.ui.shared.components.models.CTRecipeListItemComponentPresentation
import com.cooktogether.ui.shared.components.models.CTRecipeListSectionComponentPresentation
import com.cooktogether.ui.shared.components.models.CTRecipeSingleSectionComponentPresentation

internal object RecipesHomeStateUtils {
    val fake =
        RecipesHomeState(
            sections =
                listOf(
                    Section.SingleRecipe(
                        model =
                            CTRecipeSingleSectionComponentPresentation(
                                showIndicator = false,
                                title = "Almoço",
                                actionTitle = "VER MAIS",
                                action = { },
                                items = listOf(makeSingleCard()),
                            ),
                    ),
                    Section.RecipeCarousel(
                        model =
                            CTRecipeCarouselSectionComponentPresentation(
                                title = "Almoço",
                                actionTitle = "VER MAIS",
                                action = { },
                                items =
                                    listOf(
                                        makeSection(),
                                        makeSection(),
                                        makeSection(),
                                    ),
                            ),
                    ),
                    Section.SingleRecipe(
                        model =
                            CTRecipeSingleSectionComponentPresentation(
                                showIndicator = true,
                                title = "Almoço",
                                actionTitle = "VER MAIS",
                                action = { },
                                items = listOf(makeSingleCard()),
                            ),
                    ),
                    Section.RecipeCarousel(
                        model =
                            CTRecipeCarouselSectionComponentPresentation(
                                title = "Almoço",
                                actionTitle = "VER MAIS",
                                action = { },
                                items =
                                    listOf(
                                        makeSection(),
                                        makeSection(),
                                        makeSection(),
                                    ),
                            ),
                    ),
                    Section.RecipeList(
                        model =
                            CTRecipeListSectionComponentPresentation(
                                title = "Receitas",
                                items =
                                    listOf(
                                        makeListCard(),
                                        makeListCard(),
                                        makeListCard(),
                                        makeListCard(),
                                    ),
                            ),
                    ),
                ),
        )

    fun makeSection() =
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
                    widthCard = 264,
                    fontSizeTitle = 24,
                    fontSizeUserName = 16,
                ),
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

    fun makeListCard() =
        CTRecipeListItemComponentPresentation(
            imageUrl = "https://static.itdg.com.br/images/622-auto/3e947dc77ac3e8275f70e73414d816d3/capa.jpg",
            recipeTitle = "Ovo Frito",
            prepareTime = "20min",
            favoriteRecipe = false,
            favoriteRecipeAction = {},
        )
}