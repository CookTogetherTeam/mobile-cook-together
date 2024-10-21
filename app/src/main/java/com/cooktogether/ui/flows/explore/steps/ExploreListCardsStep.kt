package com.cooktogether.ui.flows.explore.steps

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cooktogether.R
import com.cooktogether.ui.flows.explore.steps.state.ExploreRecipesState
import com.cooktogether.ui.flows.explore.steps.state.utils.ExploreRecipesStateUtils
import com.cooktogether.ui.shared.components.steps.CTRecipeCardComponentStep
import com.cooktogether.ui.theme.CTColor

@Composable
fun ExploreListCardsStep(
    state: ExploreRecipesState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        SearchField()
        Spacer(modifier = Modifier.height(16.dp))
        RecipeCardGrid(state)
    }
}

@Composable
private fun RecipeCardGrid(
    state: ExploreRecipesState,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        content = {
            items(state.list.size) { index ->
                CTRecipeCardComponentStep(
                    model = state.list[index]
                )
            }
        }
    )
}

@Composable
private fun SearchField() {
    val inputValue = remember { mutableStateOf("") }
    TextField(
        value = inputValue.value,
        onValueChange = { inputValue.value = it },
        singleLine = true,
        leadingIcon = {
            Icon(
                painter = rememberVectorPainter(ImageVector.vectorResource(R.drawable.ic_search)),
                contentDescription = "Ícone de pesquisa",
                modifier = Modifier.padding(end = 8.dp)
            )
        },
        textStyle = TextStyle(
            fontSize = 20.sp,
            lineHeight = 26.sp,
            fontWeight = FontWeight(700),
            color = CTColor.Dark.color,
            textAlign = TextAlign.Center,
        ),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 4.dp,
                spotColor = Color(0x0F808080),
                ambientColor = Color(0x0F808080)
            )
            .shadow(
                elevation = 4.dp,
                spotColor = Color(0x1A808080),
                ambientColor = Color(0x1A808080)
            )
            .border(
                width = 1.dp,
                color = Color(0xFFF2F2F2),
                shape = RoundedCornerShape(size = 16.dp)
            )
            .background(color = Color(0xFFF8F8F8), shape = RoundedCornerShape(size = 16.dp))
    )
}

@Composable
@Preview(showBackground = true)
fun ExploreListCardsStepPreview() {
    val items = ExploreRecipesStateUtils.fake(20);
    ExploreListCardsStep(items)
}