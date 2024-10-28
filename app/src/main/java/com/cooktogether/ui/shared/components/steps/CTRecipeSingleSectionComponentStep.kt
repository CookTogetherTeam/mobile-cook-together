package com.cooktogether.ui.shared.components.steps

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.cooktogether.ui.shared.components.models.CTRecipeSingleSectionComponentPresentation
import com.cooktogether.ui.theme.CTColor

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CTRecipeSingleSectionComponentStep(model: CTRecipeSingleSectionComponentPresentation) {

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
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        CTRecipeCardComponentStep(model = model.item)
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeSingleSectionPreview() {
    CTRecipeSingleSectionComponentStep(
        model =
            CTRecipeSingleSectionComponentPresentation(
                title = "Receitas do Dia",
                actionTitle = "Ver mais".uppercase(),
                item = CTRecipeCardComponentPresentation(
                    imageUrl = "https://static.itdg.com.br/images/622-auto/3e947dc77ac3e8275f70e73414d816d3/capa.jpg",
                    prepareTime = "20min",
                    recipeTitle = "Ovo Frito",
                    tags = listOf("Café", "Almoço", "Café"),
                    userName = "John Doe",
                    favoriteRecipe = false,
                    measurements =
                    CTRecipeCardComponentMeasurementsPresentation(
                        widthCard = 361,
                        fontSizeTitle = 24,
                        fontSizeUserName = 16,
                    ),
                ),
            ),
    )
}