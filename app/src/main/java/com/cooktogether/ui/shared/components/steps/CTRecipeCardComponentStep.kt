package com.cooktogether.ui.shared.components.steps

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.cooktogether.ui.shared.components.models.CTRecipeCardComponentMeasurementsPresentation
import com.cooktogether.ui.shared.components.models.CTRecipeCardComponentPresentation
import com.cooktogether.ui.theme.CTColor

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
internal fun CTRecipeCardComponentStep(
    modifier: Modifier = Modifier,
    model: CTRecipeCardComponentPresentation,
) {
    Card(
        modifier =
            modifier
                .padding(8.dp)
                .modifyIfNull(model.measurements.widthCard?.dp) {
                    fillMaxWidth()
                }.modifyIfNotNull(model.measurements.widthCard?.dp) {
                    width(it)
                },
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
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Box {
                AsyncImage(
                    model = model.imageUrl,
                    contentDescription = "Recipe Image",
                    contentScale = ContentScale.FillBounds,
                    modifier =
                        Modifier
                            .height(200.dp),
                )
                Text(
                    text = model.prepareTime,
                    color = CTColor.Dark.color,
                    modifier =
                        Modifier
                            .padding(8.dp)
                            .background(
                                Color(0xFFF8F8F8),
                                shape = RoundedCornerShape(16.dp),
                            ).padding(horizontal = 8.dp, vertical = 4.dp)
                            .align(Alignment.TopStart),
                )
            }
            Column(
                modifier =
                    Modifier
                        .padding(horizontal = 12.dp)
                        .padding(bottom = 12.dp),
            ) {
                FlowRow(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    model.tags.forEach { hashtag ->
                        Text(
                            text = "#$hashtag",
                            color = CTColor.Dark.color,
                            modifier =
                                Modifier
                                    .background(CTColor.Primary.color, shape = RoundedCornerShape(16.dp))
                                    .padding(horizontal = 8.dp, vertical = 4.dp),
                        )
                    }
                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                ) {
                    Text(
                        text = model.recipeTitle,
                        fontSize = model.measurements.fontSizeTitle.sp,
                        color = CTColor.Dark.color,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier,
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.weight(9f),
                        ) {
                            Image(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Author Icon",
                            )
                            Text(
                                text = model.userName,
                                fontSize = model.measurements.fontSizeUserName.sp,
                                color = CTColor.Dark.color,
                            )
                        }
                        IconButton(
                            onClick = model.favoriteRecipeAction,
                            modifier = Modifier.size(24.dp).weight(1f),
                        ) {
                            Image(
                                imageVector = Icons.Default.FavoriteBorder,
                                contentDescription = "Heart Icon",
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun RecipeCardPreview() {
    CTRecipeCardComponentStep(
        model =
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
                        widthCard = 361,
                        fontSizeTitle = 24,
                        fontSizeUserName = 16,
                    ),
            ),
    )
}

inline fun <P> Modifier.modifyIfNotNull(
    value: P?,
    map: Modifier.(P) -> Modifier,
): Modifier = if (value != null) map(value) else this

inline fun <P> Modifier.modifyIfNull(
    value: P?,
    map: Modifier.() -> Modifier,
): Modifier = if (value == null) map() else this