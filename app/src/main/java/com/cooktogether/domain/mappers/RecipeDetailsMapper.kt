package com.cooktogether.domain.mappers

import com.cooktogether.domain.models.Ingredients
import com.cooktogether.domain.models.Recipe
import com.cooktogether.ui.flows.details.IngredientState
import com.cooktogether.ui.flows.details.RecipeDetailsState

object RecipeDetailsMapper {

    fun Recipe.toDetailsState() : RecipeDetailsState {
        return RecipeDetailsState(
            title = title,
            author = user,
            ingredients = ingredients.toIngredientsState(),
            steps = preparationMethod,
            imageUrl = image
        )
    }

    private fun List<Ingredients>.toIngredientsState() : List<IngredientState> {
        return this.map {
            IngredientState(
                name = it.name,
                quantity = it.quantity,
                unit = it.unit
            )
        }
    }
}