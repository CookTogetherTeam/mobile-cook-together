import com.cooktogether.domain.models.Recipe
import com.cooktogether.ui.flows.explore.steps.state.ExploreRecipesState
import com.cooktogether.ui.flows.home.steps.state.RecipesHomeState
import com.cooktogether.ui.flows.home.steps.state.Section
import com.cooktogether.ui.shared.components.models.CTRecipeCardComponentMeasurementsPresentation
import com.cooktogether.ui.shared.components.models.CTRecipeCardComponentPresentation
import com.cooktogether.ui.shared.components.models.CTRecipeCarouselSectionComponentPresentation
import com.cooktogether.ui.shared.components.models.CTRecipeSingleSectionComponentPresentation

object RecipesMapper {

    fun List<Recipe>.toExploreState(): ExploreRecipesState {
        return ExploreRecipesState(
            list = this.map {
                it.toSingleCardSectionPresentation()
            }
        )
    }

    fun List<Recipe>.toRecipesHomeState(): RecipesHomeState {
        val sections = mutableListOf<Section>()

        val firstRecipe = this.firstOrNull()
        val remainingRecipes = this.drop(1)

        firstRecipe?.let { recipe ->
            sections.add(Section.SingleRecipe(recipe.toSingleSectionPresentation()))
        }

        remainingRecipes
            .groupBy { Pair(it.category, it.categoryName) }
            .filter { it.key.first != firstRecipe?.category }
            .forEach { (identifiers, recipes) ->
                val (categoryId, categoryName) = identifiers
                sections.add(
                    Section.RecipeCarousel(
                        recipes.toSectionPresentation(
                            categoryName = categoryName,
                            categoryId = categoryId,
                        )
                    )
                )
            }

        return RecipesHomeState(sections)
    }


    private fun Recipe.toSingleSectionPresentation(): CTRecipeSingleSectionComponentPresentation {
        return CTRecipeSingleSectionComponentPresentation(
            title = categoryName,
            actionTitle = "VER MAIS",
            item = toSingleCardSectionPresentation(),
            categoryId = category
        )
    }

    private fun List<Recipe>.toSectionPresentation(
        categoryName: String,
        categoryId: Int,
    ): CTRecipeCarouselSectionComponentPresentation {
        return CTRecipeCarouselSectionComponentPresentation(
            title = categoryName,
            actionTitle = "VER MAIS",
            items = map { it.toCardSectionPresentation() },
            categoryId = categoryId
        )
    }

    private fun Recipe.toSingleCardSectionPresentation(): CTRecipeCardComponentPresentation {
        return CTRecipeCardComponentPresentation(
            imageUrl = image ?: "",
            recipeTitle = title,
            tags = listOf(categoryName),
            userName = user,
            favoriteRecipe = false,
            measurements = CTRecipeCardComponentMeasurementsPresentation(
                widthCard = 361,
                fontSizeTitle = 24,
                fontSizeUserName = 16
            ),
            id = id
        )
    }

    private fun Recipe.toCardSectionPresentation(): CTRecipeCardComponentPresentation {
        return CTRecipeCardComponentPresentation(
            imageUrl = image ?: "",
            recipeTitle = title,
            tags = listOf(categoryName),
            userName = user,
            favoriteRecipe = false,
            measurements = CTRecipeCardComponentMeasurementsPresentation(
                widthCard = 264,
                fontSizeTitle = 20,
                fontSizeUserName = 14
            ),
            id = id
        )
    }
}
