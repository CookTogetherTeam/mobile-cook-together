package com.cooktogether.ui.shared.components.steps

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.cooktogether.ui.shared.components.models.CTRecipeListItemComponentPresentation
import com.cooktogether.ui.theme.CTColor

@Composable
internal fun CTRecipeListItemComponentStep(
    modifier: Modifier = Modifier,
    model: CTRecipeListItemComponentPresentation,
) {
    Card(
        modifier =
            modifier
                .padding(8.dp)
                .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation =
            CardDefaults.cardElevation(
                defaultElevation = 4.dp,
            ),
        colors =
            CardDefaults.cardColors(
                containerColor = CTColor.Background.color,
            ),
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                model = model.imageUrl,
                contentDescription = "Recipe Image",
                contentScale = ContentScale.FillBounds,
                modifier =
                    Modifier
                        .height(96.dp)
                        .fillMaxWidth(0.3f)
                        .clip(RoundedCornerShape(8.dp)),
            )

            Spacer(modifier = Modifier.size(12.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(32.dp),
                modifier =
                    Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
            ) {
                Text(
                    text = model.recipeTitle,
                    fontSize = 18.sp,
                    color = CTColor.Dark.color,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold,
                    modifier =
                        Modifier
                            .padding(vertical = 8.dp)
                            .fillMaxWidth(),
                )

                Text(
                    text = model.prepareTime,
                    color = CTColor.Dark.color,
                    modifier =
                        Modifier
                            .background(CTColor.Primary.color, shape = RoundedCornerShape(16.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                            .align(Alignment.Start),
                )
            }

            IconButton(
                onClick = model.favoriteRecipeAction,
            ) {
                Image(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "Heart Icon",
                )
            }
        }
    }
}

@Preview
@Composable
fun RecipeListItemComponent() {
    CTRecipeListItemComponentStep(
        model =
            CTRecipeListItemComponentPresentation(
                imageUrl = "https://static.itdg.com.br/images/622-auto/3e947dc77ac3e8275f70e73414d816d3/capa.jpg",
                recipeTitle = "Célula das receitas",
                prepareTime = "3min",
                favoriteRecipe = false,
                favoriteRecipeAction = {},
            ),
    )
}