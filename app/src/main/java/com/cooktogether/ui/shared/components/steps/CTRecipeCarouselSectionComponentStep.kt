package com.cooktogether.ui.shared.components.steps

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cooktogether.ui.shared.components.models.CTRecipeCardComponentMeasurementsPresentation
import com.cooktogether.ui.shared.components.models.CTRecipeCardComponentPresentation
import com.cooktogether.ui.shared.components.models.CTRecipeCarouselSectionComponentPresentation
import com.cooktogether.ui.theme.CTColor

@Composable
fun CTRecipeCarouselSectionComponentStep(model: CTRecipeCarouselSectionComponentPresentation) {
    Column(
        modifier =
            Modifier
                .background(CTColor.Background.color)
                .padding(8.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
        ) {
            Text(
                text = model.title,
                fontSize = 18.sp,
                color = CTColor.Dark.color,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.weight(1f),
            )
            Text(
                text = model.actionTitle,
                fontSize = 16.sp,
                color = CTColor.Dark.color,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Normal,
                modifier =
                    Modifier
                        .clickable(onClick = model.action),
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        LazyRow {
            items(model.items.toList()) { recipeCard ->
                CTRecipeCardComponentStep(model = recipeCard)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeCarouselPreview() {
    CTRecipeCarouselSectionComponentStep(
        model =
            CTRecipeCarouselSectionComponentPresentation(
                title = "Receitas do Dia",
                actionTitle = "Ver mais".uppercase(),
                action = { },
                items =
                    listOf(
                        CTRecipeCardComponentPresentation(
                            imageUrl = "https://static.itdg.com.br/images/622-auto/3e947dc77ac3e8275f70e73414d816d3/capa.jpg",
                            prepareTime = "20min",
                            recipeTitle = "Ovo Frito",
                            tags = listOf("Café", "Almoço"),
                            userName = "John Doe",
                            favoriteRecipe = false,
                            favoriteRecipeAction = { },
                            measurements =
                                CTRecipeCardComponentMeasurementsPresentation(
                                    widthCard = 200,
                                    fontSizeTitle = 24,
                                    fontSizeUserName = 16,
                                ),
                        ),
                        CTRecipeCardComponentPresentation(
                            imageUrl = "https://static.itdg.com.br/images/622-auto/3e947dc77ac3e8275f70e73414d816d3/capa.jpg",
                            prepareTime = "10min",
                            recipeTitle = "Salada Colorida",
                            tags = listOf("Almoço", "Saudável"),
                            userName = "Jane Smith",
                            favoriteRecipe = true,
                            favoriteRecipeAction = { },
                            measurements =
                                CTRecipeCardComponentMeasurementsPresentation(
                                    widthCard = 200,
                                    fontSizeTitle = 24,
                                    fontSizeUserName = 16,
                                ),
                        ),
                        CTRecipeCardComponentPresentation(
                            imageUrl = "https://static.itdg.com.br/images/622-auto/3e947dc77ac3e8275f70e73414d816d3/capa.jpg",
                            prepareTime = "30min",
                            recipeTitle = "Panquecas",
                            tags = listOf("Café", "Doce"),
                            userName = "Emily Davis",
                            favoriteRecipe = false,
                            favoriteRecipeAction = { },
                            measurements =
                                CTRecipeCardComponentMeasurementsPresentation(
                                    widthCard = 200,
                                    fontSizeTitle = 24,
                                    fontSizeUserName = 16,
                                ),
                        ),
                    ),
            ),
    )
}