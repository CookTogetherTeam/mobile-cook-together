package com.cooktogether.ui.shared.components.steps

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cooktogether.ui.shared.components.models.CTRecipeListItemComponentPresentation
import com.cooktogether.ui.shared.components.models.CTRecipeListSectionComponentPresentation
import com.cooktogether.ui.theme.CTColor

@Composable
fun CTRecipeListSectionComponent(
    modifier: Modifier = Modifier,
    model: CTRecipeListSectionComponentPresentation,
) {
    Column {
        Text(
            text = model.title,
            fontSize = 18.sp,
            color = CTColor.Dark.color,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold,
            modifier =
                Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 8.dp),
        )

        Column {
            model.items.forEach { recipeItem ->
                CTRecipeListItemComponentStep(
                    model = recipeItem,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeListSectionPreview() {
    CTRecipeListSectionComponent(
        model =
            CTRecipeListSectionComponentPresentation(
                title = "Receitas Populares",
                items =
                    listOf(
                        CTRecipeListItemComponentPresentation(
                            imageUrl = "https://static.itdg.com.br/images/622-auto/3e947dc77ac3e8275f70e73414d816d3/capa.jpg",
                            recipeTitle = "Ovo Frito",
                            prepareTime = "20min",
                            favoriteRecipe = false,
                            favoriteRecipeAction = { },
                        ),
                        CTRecipeListItemComponentPresentation(
                            imageUrl = "https://static.itdg.com.br/images/622-auto/3e947dc77ac3e8275f70e73414d816d3/capa.jpg",
                            recipeTitle = "Salada Colorida",
                            prepareTime = "10min",
                            favoriteRecipe = true,
                            favoriteRecipeAction = { },
                        ),
                        CTRecipeListItemComponentPresentation(
                            imageUrl = "https://static.itdg.com.br/images/622-auto/3e947dc77ac3e8275f70e73414d816d3/capa.jpg",
                            recipeTitle = "Panquecas",
                            prepareTime = "30min",
                            favoriteRecipe = false,
                            favoriteRecipeAction = { },
                        ),
                        CTRecipeListItemComponentPresentation(
                            imageUrl = "https://static.itdg.com.br/images/622-auto/3e947dc77ac3e8275f70e73414d816d3/capa.jpg",
                            recipeTitle = "Mousse de Chocolate",
                            prepareTime = "15min",
                            favoriteRecipe = true,
                            favoriteRecipeAction = { },
                        ),
                        CTRecipeListItemComponentPresentation(
                            imageUrl = "https://static.itdg.com.br/images/622-auto/3e947dc77ac3e8275f70e73414d816d3/capa.jpg",
                            recipeTitle = "Sopa de Legumes",
                            prepareTime = "45min",
                            favoriteRecipe = false,
                            favoriteRecipeAction = { },
                        ),
                    ),
            ),
    )
}