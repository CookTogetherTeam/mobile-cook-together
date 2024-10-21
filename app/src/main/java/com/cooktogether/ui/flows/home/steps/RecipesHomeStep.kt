package com.cooktogether.ui.flows.home.steps

import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cooktogether.ui.flows.home.steps.state.RecipesHomeState
import com.cooktogether.ui.flows.home.steps.state.Section
import com.cooktogether.ui.flows.home.steps.state.utils.RecipesHomeStateUtils
import com.cooktogether.ui.shared.components.models.CTRecipeCarouselSectionComponentPresentation
import com.cooktogether.ui.shared.components.models.CTRecipeListSectionComponentPresentation
import com.cooktogether.ui.shared.components.models.CTRecipeSingleSectionComponentPresentation
import com.cooktogether.ui.shared.components.steps.CTRecipeCarouselSectionComponentStep
import com.cooktogether.ui.shared.components.steps.CTRecipeListSectionComponent
import com.cooktogether.ui.shared.components.steps.CTRecipeSingleSectionComponentStep
import com.cooktogether.ui.theme.CTColor

@Composable
fun RecipesHomeStep(
    modifier: Modifier = Modifier,
    state: RecipesHomeState,
) {
    LazyColumn(
        modifier =
            modifier
                .background(CTColor.Background.color),
    ) {
        items(state.sections) { section ->
            when (section) {
                is Section.SingleRecipe -> SingleRecipeSection(section.model)
                is Section.RecipeCarousel -> RecipeCarouselSection(section.model)
                is Section.RecipeList -> RecipeListSection(section.model)
            }
        }
    }
}

@Composable
fun SingleRecipeSection(model: CTRecipeSingleSectionComponentPresentation) {
    CTRecipeSingleSectionComponentStep(model = model)
}

@Composable
fun RecipeCarouselSection(model: CTRecipeCarouselSectionComponentPresentation) {
    CTRecipeCarouselSectionComponentStep(model = model)
}

@Composable
fun RecipeListSection(model: CTRecipeListSectionComponentPresentation) {
    CTRecipeListSectionComponent(model = model)
}

@Preview(showBackground = true)
@Composable
fun RecipesHomeStepPreview() {
    RecipesHomeStep(state = RecipesHomeStateUtils.fake)
}