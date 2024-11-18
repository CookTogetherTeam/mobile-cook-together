package com.cooktogether.data.mappers

import com.cooktogether.data.models.IngredientsResponse
import com.cooktogether.data.models.RecipeResponse
import com.cooktogether.domain.models.Ingredients
import com.cooktogether.domain.models.Recipe

internal object RecipeResponseMapper {

    fun RecipeResponse.toRecipeDomain() : Recipe {
        return Recipe(
            id,
            user,
            title,
            ingredients.toDomainIngredients(),
            preparationMethod,
            image,
            category,
            categoryName
        )
    }

    fun List<RecipeResponse>.toDomain() : List<Recipe> {
        return this.map {
            Recipe(
                it.id,
                it.user,
                it.title,
                it.ingredients.toDomainIngredients(),
                it.preparationMethod,
                it.image,
                it.category,
                it.categoryName
            )
        }
    }

    private fun List<IngredientsResponse>.toDomainIngredients() : List<Ingredients> {
        return this.map { Ingredients(
            name = it.name,
            quantity = it.quantity,
            unit = it.unit
        ) }
    }
}
